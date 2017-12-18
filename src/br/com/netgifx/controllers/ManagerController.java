package br.com.netgifx.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import br.com.netgifx.dao.CategoriaDAO;
import br.com.netgifx.dao.FilmeDAO;
import br.com.netgifx.entity.Categoria;
import br.com.netgifx.entity.Filme;
import br.com.netgifx.entity.Idioma;
import br.com.netgifx.util.HibernateUtil;


@Controller
public class ManagerController {

	private String msg = "";
	private String errMsg = "";
	protected Session session = null;
	protected Transaction transaction = null;
	
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView inicialManager(Model m){
		ModelAndView model = new ModelAndView("Manager/manager");
		List<Idioma> idiomas = Arrays.asList(Idioma.values());
		model.addObject("idiomas", idiomas);
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			model.addObject("categorias", getCategorias());
			transaction.commit();
		} catch (Exception e) {
			m.addAttribute("errMsg", "Erro ao carregar as categorias");
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/manager/newMovie", method = RequestMethod.POST)
	public ModelAndView newMovie(@RequestParam("categoria") int catid, @RequestParam("classificacao") String clasf ,
			@RequestParam("titulo") String titu, @RequestParam("descricao") String desc,
			@RequestParam("idioma") String idio, 
			@RequestParam("file") MultipartFile file, Model model, HttpServletResponse resp){
		
		ModelAndView m = new ModelAndView("Manager/manager");
		List<Idioma> idiomas = Arrays.asList(Idioma.values());
		m.addObject("idiomas", idiomas);
		// upload movie
		if(!file.isEmpty()){
			
			// generate uniqueidentifier
			//String id = UUID.randomUUID().toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
			// get file stream
			FileInputStream stream = null;
			InputStream iStream = null;
			try {
				
				// create snapshot from image
				ImageReader reader = (ImageReader)ImageIO.getImageReadersByFormatName("gif").next();
				stream = (FileInputStream) file.getInputStream();
				iStream = file.getInputStream();
				
				// ftp upload
				String fileName = UUID.randomUUID().toString() + ".gif";
				fileUpload(iStream, (fileName));
				
				// file stream to byte conversion
				//byte[] imageBytes = IOUtils.toByteArray(stream);
				
				ImageInputStream iis = ImageIO.createImageInputStream(stream);
				reader.setInput(iis);
				int numberOfImages = reader.getNumImages(true);
				if (numberOfImages >= 100) numberOfImages = 99;
				BufferedImage bi = reader.read(numberOfImages-2);
				byte[] snap = null;

				try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
		            ImageIO.write(bi, "gif", out);
		            snap = out.toByteArray();
		            InputStream ins = new ByteArrayInputStream(snap);
					fileUpload(ins, ("tn_"+ fileName));
		        }
				
		
				// construct object
				Filme f = new Filme();
				Categoria cat = new Categoria();
				cat.setId(catid);
				f.setCategoria(cat);
				
				f.setClassificacao(clasf);
				f.setDataCadastro(LocalDate.now());
				f.setDescricao(desc);
				f.setIdioma(idio);
				f.setTitulo(titu);
				
				// add movie path
				f.setFileImagePath("http://www.conchasbrasil.org.br/netgifx/"+ fileName);
				f.setPreviewImagePath("http://www.conchasbrasil.org.br/netgifx/tn_"+ fileName);
				
				// add movies
				//f.setFileImage(imageBytes);
				//f.setPreviewImage(snap);
				
				// persist
				try {
					session = HibernateUtil.getSessionFactory().getCurrentSession();
					FilmeDAO dao = new FilmeDAO(session);
					transaction = session.beginTransaction();
					dao.insert(f);

					msg = "Filme cadastrado";
					
					m.addObject("categorias", getCategorias());
		
				} catch (Exception e) {
					errMsg = e.getMessage();
					e.printStackTrace();
				} finally {
					transaction.commit();
				}

			} catch (Exception e) {
				errMsg = e.getMessage();
				e.printStackTrace();
			} 
			
		} else {
			errMsg = "Selecione um arquivo para upload";			
		}
		model.addAttribute("errMsg", errMsg);
		model.addAttribute("msg", msg);
		
		
		return m;
	}
	
	@RequestMapping(value = "/manager/categorias")
	public ModelAndView inicialCategorias(Model m){
		ModelAndView model = new ModelAndView("Manager/categorias");
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			model.addObject("categorias", getCategorias());
			transaction.commit();
		} catch (Exception e) {
			m.addAttribute("errMsg", "Erro ao carregar as categorias");
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/manager/newCategory", method = RequestMethod.POST)
	public ModelAndView newCategory(Categoria categoria, Model m){
		ModelAndView model = new ModelAndView("Manager/categorias");
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			CategoriaDAO dao = new CategoriaDAO(session);
			transaction = session.beginTransaction();
			dao.insert(categoria);
			transaction.commit();
			
			m.addAttribute("msg", "Nova categoria criada");
			
			try {
				model.addObject("categorias", getCategorias());
				
			} catch (Exception e) {
				m.addAttribute("errMsg", "Erro ao carregar as categorias");
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			m.addAttribute("errMsg", e.getMessage());
			e.printStackTrace();
		}
	
		return model;
	}
	
	private List<Categoria> getCategorias() throws Exception {
		
		CategoriaDAO dao = new CategoriaDAO(session);
		//transaction = session.beginTransaction();
		List<Categoria> categorias = dao.listAll();
		//transaction.commit();
		return categorias;
	}
	
	private void fileUpload(InputStream inputFile, String outputFileName){
		
		FTPClient client = new FTPClient();
		try {
			// pass directory path on server to connect
			client.connect("ftp.conchasbrasil.org.br");

			// pass username and password, returned true if authentication is successful
			boolean login = client.login("conchasbrasil", "c0kgm2oiff2X");

			if (login) {
				client.setFileType(FTP.BINARY_FILE_TYPE);
				boolean uploaded = client.storeFile("web/netgifx/" + outputFileName, inputFile);
				if (uploaded) {
					System.out.println("File uploaded successfully !");
				} else {
					System.out.println("Error in uploading file !");
				}

				// logout the user, returned true if logout successfully
				boolean logout = client.logout();
				if (logout) {
					System.out.println("Connection close...");
				}
			} else {
				System.out.println("Connection fail...");
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
