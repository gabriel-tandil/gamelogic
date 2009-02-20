/*
* updatemanager.Visual
* Version 1.0
* Date: 27/10/2008
*/
package client.update;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * Muestra por pantalla las barras de progreso y la barra de estado de 
 * la descarga de actualizaciones del cliente.
 * 
 * @author Guillermo Fiorini, Gabriel Alvarez, Gouvert Rodolfo
 * @version 1.0
 */
public class Visual extends javax.swing.JFrame {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Barra de progreso de archivo. */
	private JProgressBar jProgressBar1;
	
	/** TextArea para mostrar los features/bugfixes. */
	private JTextArea jTextArea1;
	
	/** Barra de progreso global. */
	private JProgressBar jProgressBar2;
	
	/** Listado de archivos a descargar. */
	private Vector<FileData> filesToUpdate; 
	
	/** Instancia de UpdateManager para reportar el resultado. */
	private MyUpdateManager reportTo;
	
	/** Scroll para el textArea. */
	private JScrollPane jScrollPane1;

	/** Label que detalle el estado de la descarga. */
	private JLabel jLabel1;

	/**
	 * Inicializa una nueva ventana de descarga.
	 * 
	 * @see MyUpdateManager
	 * 
	 * @param files Lista de archivos a descargar
	 * @param um the Instancia de UpdateManager para reportar
	 */
	public Visual(final Vector<FileData> files, final MyUpdateManager um) {
		super();
		filesToUpdate = files;
		reportTo = um;
		initGUI();
		Updater a = new Updater(filesToUpdate, jProgressBar2, 
				jProgressBar1, reportTo, jLabel1);
		a.start();
	}
	
	/**
	 * Inicializa la interfaz.
	 */
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] {0.1, 0.0, 0.0, 0.0};
			thisLayout.rowHeights = new int[] {7, 40, 40, 20};
			thisLayout.columnWeights = new double[] {0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7};
			getContentPane().setLayout(thisLayout);
			this.setResizable(false);

			jProgressBar1 = new JProgressBar();
			jProgressBar1.setToolTipText("Porcentaje de "
					+ "completitud de la descarga");
			jProgressBar1.setDoubleBuffered(true);
			jProgressBar1.setStringPainted(true);
			getContentPane().add(jProgressBar1, 
					new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, 
					GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, 
					new Insets(0, 10, 0, 10), 0, 0));

			jProgressBar2 = new JProgressBar();
			jProgressBar2.setDoubleBuffered(true);
			jProgressBar2.setStringPainted(true);
			getContentPane().add(jProgressBar2, 
					new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0, 
					GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, 
					new Insets(0, 10, 0, 10), 0, 0));
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, 
						new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, 
						GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
						new Insets(10, 10, 0, 10), 0, 0));
				{
					jTextArea1 = new JTextArea();
					jScrollPane1.setViewportView(jTextArea1);
					jTextArea1.setText("Version " 
						+ reportTo.getVersion() + "\n\n");
					jTextArea1.setWrapStyleWord(true);
					jTextArea1.setEditable(false);
				}
			}

			jTextArea1.append(reportTo.getFeature());

			jLabel1 = new JLabel();
			getContentPane().add(jLabel1, 
					new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, 
					GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, 
					new Insets(0, 10, 0, 10), 0, 0));
			jLabel1.setText("Inicializando actualizacion...");

			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
