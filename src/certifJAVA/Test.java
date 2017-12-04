package certifJAVA;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Test {

	// Imagenes del Caracter
	static final File baseCharacter = new File("character/jumanjaBase-01.png");
	static final File dormidoCharacter = new File("character/jumanjaDormido.png");
	static final File enojadoCharacter = new File("character/jumanjaEnojado.png");
	static final File sonrisaCharacter = new File("character/jumanjaSonrisa.png");
	static final File tristezaCharacter = new File("character/jumanjaTristeza.png");
		
	// Directorio a abrir
    static final File dir = new File("questions");

    // array con las extensiones de archivo validas
    static final String[] EXTENSIONS = new String[]{
        "gif", "png", "jpg" // and other formats you need
    };
    
    // filtro segun las extensiones
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    public int generarNumeroAleatorioPar(int limite) {
        Random random = new Random();
        int number = random.nextInt(limite);
        while (!esParElNumero(number)) {
            number = random.nextInt(limite);
        }
        return number;
    }

    public int generarNumeroAleatorioImPar(int limite) {
        Random random = new Random();
        int number = random.nextInt(limite);
        while (esParElNumero(number)) {
            number = random.nextInt(limite);
        }
        return number;
    }

    private boolean esParElNumero(int number) {
        return (number % 2) == 0;
    }
    
    public void init() {

	    	JPanel panel = new JPanel();
	    	BufferedImage myPicture = null;
	    	BufferedImage myCharacter = null;

	    	Random randomGenerator = new Random();
	    	int randomInt = 0;
	    	int response = 0;
	    	int counter = 0;
	    	int correctas = 0;
	    	int incorrectas = 0;
	    	
    		String frase = "";

        if (dir.isDirectory()) { // si es un directorio
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
    	    	 		panel = new JPanel(new BorderLayout());

    	    	 		Properties prop = new Properties();
    	    	 		InputStream input = null;
    	    	    	
    	    	 		String correct = "";

                //Nombre de imagen pero sin extension
    	    	 		String fileName = f.getName();
	    	    	 	int pos = f.getName().lastIndexOf(".");
	    	    	 	if (pos > 0) {
	    	    	 	    fileName = fileName.substring(0, pos);
	    	    	 	}
	    	    	 	try {

	    	    			input = new FileInputStream(dir + "/" + fileName + ".properties");

	    	    			prop.load(input);
	    	    			
	    	    			// set the properties value
	    	    			correct = prop.getProperty("correct");

	    	    		} catch (IOException ex) {
	    	    			ex.printStackTrace();
	    	    		} finally {
	    	    			if (input != null) {
	    	    				try {
	    	    					input.close();
	    	    				} catch (IOException e) {
	    	    					e.printStackTrace();
	    	    				}
	    	    			}
	    	    		}
                try {
	    				// mi Caracter

	                //Adicionar mi character
	                if(counter == 0) {
	                		frase = "Ok, Comencemos...";
	                		myCharacter = ImageIO.read(baseCharacter);
	                } else {
		                //int randomInt = randomGenerator.nextInt(4);
		                switch (randomInt){
			                case 0: 
		    	    					myCharacter = ImageIO.read(dormidoCharacter);
		    	    					frase = "Bien...Esa respuesta era obvia";
		    	    					break;
			                case 1: 
		    	    					myCharacter = ImageIO.read(enojadoCharacter);
		    	    					frase = "No!... Increíble que eligieras mal!";
		    	    					break;
			                case 2: 
		    	    					myCharacter = ImageIO.read(sonrisaCharacter);
		    	    					frase = "Excelente, esa era difícil!";
		    	    					break;
			                case 3: 
		    	    					myCharacter = ImageIO.read(tristezaCharacter);
		    	    					frase = "Parece que necesitas repasar mas!";
		    	    					break;
			                case 4: 
		    	    					myCharacter = ImageIO.read(sonrisaCharacter);
		    	    					frase = "Excelente, esa era difícil!";
		    	    					break;
			                case 5: 
		    	    					myCharacter = ImageIO.read(tristezaCharacter);
		    	    					frase = "Parece que necesitas repasar mas!";
		    	    					break;
			                default: 
		    	    					myCharacter = ImageIO.read(baseCharacter);
		    	    					frase = "Bien, sigue así...!";
		    	    					break;
		                }	                	
	                }
	                panel.add(new JLabel(new ImageIcon(myCharacter)), BorderLayout.WEST);
	                panel.add(new JLabel(frase), BorderLayout.SOUTH);

	                //img = ImageIO.read(f);
    	    	    			myPicture = ImageIO.read(f);

                    // mostrar progreso consola
                    System.out.println("image: " + f.getName() + " correct:" + correct);
                } catch (final IOException e) {
                    // handle errors here
                }

                //Adicionar mi pregunta
                panel.add(new JLabel(new ImageIcon(myPicture)), BorderLayout.CENTER);

		    	    	Object[] options = {"D.", "C.", "B.", "A."};
		    	    	JOptionPane pane = new JOptionPane();
		    	    	response = pane.showOptionDialog(null, panel, "certifJAVA", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

		    	    	String strResponse = "";
		    	    	switch(response) {
		    	    		case 0:
			    	    		strResponse = "D";
			    	    		break;
		    	    		case 1:
			    	    		strResponse = "C";
			    	    		break;
		    	    		case 2:
			    	    		strResponse = "B";
			    	    		break;
		    	    		default:
			    	    		strResponse = "A";
			    	    		break;
		    	    	}

		    	    	//Respuesta Correcta
		    	    	if(correct.equals(strResponse)){
		    	    		//randomInt serà par
		    	    		randomInt = generarNumeroAleatorioPar(6);
		    	    		correctas++;
		    	    	} else {
		    	    		//randomInt serà impar
		    	    		randomInt = generarNumeroAleatorioImPar(6);
		    	    		incorrectas++;
		    	    	}
		    	    	System.out.println("response: " + response + 
		    	    			" strResponse: " + strResponse + 
		    	    			" randomInt:" + randomInt);
		    	    	counter++;
            }
            
            //Final
            try {
	            panel = new JPanel(new BorderLayout());
	        		if(correctas > incorrectas) {
						myCharacter = ImageIO.read(sonrisaCharacter);
						frase = "Felicidades!: " + correctas + "/" + (correctas+incorrectas);
	        			
	        		} else {
						myCharacter = ImageIO.read(tristezaCharacter);
						frase = "Parece que necesitas repasar mas!" + correctas + "/" + (correctas+incorrectas);;
	        			
	        		}
            } catch (final IOException e) {
                // handle errors here
            }

            JOptionPane pane = new JOptionPane();
            panel.add(new JLabel(new ImageIcon(myCharacter)), BorderLayout.WEST);
            panel.add(new JLabel(frase), BorderLayout.SOUTH);

            Object[] options = {"Ok"};
		    	response = pane.showOptionDialog(null, panel, "certifJAVA", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

        } else {
        	 System.out.println("dir: is not a directory");
        }
    }
}