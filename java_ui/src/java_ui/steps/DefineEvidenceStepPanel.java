package java_ui.steps;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jpl7.Query;
import java_ui.prolog_loader.PrologLoadException;
import java_ui.prolog_loader.PrologLoader;
import java_ui.table_editor.TableEditorDialog;
import java_ui.table_editor.model_builder.EvidenceTableModelBuilder;
import java_ui.table_editor.panel.TableEditorPanel;
import java_ui.table_editor.panel.TableViewer;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class DefineEvidenceStepPanel extends StepPanel{
	
	private JButton stepButton;
	private JLabel statusLabel;
	private JLabel statusResultLabel;
	
	private TableEditorPanel tep;
	private PrologLoader loader;
	private JButton viewButton;
	
	private TableViewer viewer;
	private JButton randomButton;

	public DefineEvidenceStepPanel(String instruction, final String tableViewerTitle, final TableEditorPanel tep, final PrologLoader loader) {
		this.tep = tep;
		this.loader = loader;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{45, 320, 110, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel instructionLabel = new JLabel(instruction);
		GridBagConstraints gbc_instructionLabel = new GridBagConstraints();
		gbc_instructionLabel.gridheight = 2;
		gbc_instructionLabel.anchor = GridBagConstraints.NORTH;
		gbc_instructionLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_instructionLabel.gridwidth = 2;
		gbc_instructionLabel.insets = new Insets(5, 5, 5, 5);
		gbc_instructionLabel.gridx = 0;
		gbc_instructionLabel.gridy = 0;
		add(instructionLabel, gbc_instructionLabel);
		
		this.stepButton = new JButton("Edit");
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TableModel backup = tep.getTableModel();
					TableEditorDialog dialog = new TableEditorDialog(tep);
					
					TableModel response = dialog.getResponse();
					
					if(response != null){
						loader.loadData(response);
						
						if(loader.getStatus() == PrologLoader.StatusCode.Ok){
							statusResultLabel.setText("OK");
							getFollowingStep().enableStep();
						}
						else{
							statusResultLabel.setText("ERROR");
							getFollowingStep().disableStep();
						}
					}
					else {
						tep.setTableModel(backup);
					}
				} 
				catch (PrologLoadException e1) {
					getFollowingStep().disableStep();
					statusResultLabel.setText("ERROR");
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					disableStep();
				}
				catch (IOException e1) {
					getFollowingStep().disableStep();
					statusResultLabel.setText("ERROR");
					e1.printStackTrace();
					disableStep();
				}
			}
		});
		GridBagConstraints gbc_stepButton = new GridBagConstraints();
		gbc_stepButton.anchor = GridBagConstraints.NORTH;
		gbc_stepButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_stepButton.insets = new Insets(5, 5, 5, 5);
		gbc_stepButton.gridx = 2;
		gbc_stepButton.gridy = 0;
		add(stepButton, gbc_stepButton);
		
		randomButton = new JButton("Random");
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String input = (String) JOptionPane.showInputDialog(null, "Please, define the number of alternatives to be generated.", "Generate random evidence", JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				if(input != null){
					
					try{
						int value = Integer.parseInt(input);
						
						
						Query q = new Query("generate_random_evidence("+value+")");
						
						if(q.hasNext()){
							q.next();
						}
						
						loadTableModel();
						
						getFollowingStep().enableStep();
						
					}catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "You did not specified a correct number", "Error", JOptionPane.ERROR_MESSAGE);
						getFollowingStep().disableStep();
					}
				}
			}
		});
		GridBagConstraints gbc_randomButton = new GridBagConstraints();
		gbc_randomButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_randomButton.anchor = GridBagConstraints.SOUTH;
		gbc_randomButton.insets = new Insets(0, 5, 5, 5);
		gbc_randomButton.gridx = 2;
		gbc_randomButton.gridy = 1;
		add(randomButton, gbc_randomButton);
		
		this.statusLabel = new JLabel("Status:");
		GridBagConstraints gbc_statusLabel = new GridBagConstraints();
		gbc_statusLabel.anchor = GridBagConstraints.NORTH;
		gbc_statusLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_statusLabel.insets = new Insets(0, 5, 0, 5);
		gbc_statusLabel.gridx = 0;
		gbc_statusLabel.gridy = 2;
		add(statusLabel, gbc_statusLabel);
		
		this.statusResultLabel = new JLabel("---"); 
		GridBagConstraints gbc_statusResultLabel = new GridBagConstraints();
		gbc_statusResultLabel.anchor = GridBagConstraints.NORTH;
		gbc_statusResultLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_statusResultLabel.insets = new Insets(0, 5, 0, 5);
		gbc_statusResultLabel.gridx = 1;
		gbc_statusResultLabel.gridy = 2;
		add(statusResultLabel, gbc_statusResultLabel);
		
		viewButton = new JButton("View");
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					viewer = new TableViewer(tep.getTableModel());
					viewer.setFocusable(true);
					viewer.setTitle(tableViewerTitle);
					viewer.disableTable();
			}
		});
		GridBagConstraints gbc_viewButton = new GridBagConstraints();
		gbc_viewButton.insets = new Insets(0, 5, 5, 5);
		gbc_viewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_viewButton.anchor = GridBagConstraints.NORTH;
		gbc_viewButton.gridx = 2;
		gbc_viewButton.gridy = 2;
		add(viewButton, gbc_viewButton);
		
	}

	@Override
	public void enableStep() {
		this.stepButton.setEnabled(true);
		this.viewButton.setEnabled(true);
		this.randomButton.setEnabled(true);
	}

	@Override
	public void disableStepAction() {
		this.stepButton.setEnabled(false);
		this.viewButton.setEnabled(false);
		this.randomButton.setEnabled(false);
	}
	
	
	public void cleanStepAction(){
		this.tep.setTableModel(new DefaultTableModel());
		this.statusResultLabel.setText("---");
		if(this.viewer != null){
			this.viewer.setVisible(false);
			this.viewer = null;
		}
	}
	

	public PrologLoader.StatusCode getLoaderStatus(){
		return this.loader.getStatus();
	}
	
	public void setTableModel(TableModel tm) throws PrologLoadException{
		this.tep.setTableModel(tm);
		this.enableStep();
		try {
			this.loader.loadData(tm);
			this.statusResultLabel.setText("OK");
		} catch (PrologLoadException e) {
			this.statusResultLabel.setText("ERROR");
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			getFollowingStep().disableStep();
			throw e;
		}
	}
	
	
	private void loadTableModel(){
		TableModel tm = EvidenceTableModelBuilder.loadEvidence();
		this.tep.setTableModel(tm);
	}
}
