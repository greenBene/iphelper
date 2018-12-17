package click.benedikt.iphelper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class gui {
    private JTextField rowField;
    private JTextField pathField;
    private JButton SELECTButton;
    private JButton ADDCOUNTRYSButton;
    public JPanel ipHelperPanel;


    gui() {
        SELECTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter("Excel", "xlsx"));

                int returnVal = fc.showDialog(ipHelperPanel, "SELECT");

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //This is where a real application would open the file.
                    pathField.setText(file.getPath());
                }
            }
        });
        ADDCOUNTRYSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rowField.getText().equals("")) {
                    JOptionPane.showMessageDialog(ipHelperPanel, "Please enter a starting cell");
                }else if(pathField.getText().equals("")) {
                    JOptionPane.showMessageDialog(ipHelperPanel, "Please select a excel file");
                } else {
                    try {
                        App.addCountryToExcel(pathField.getText(), rowField.getText());
                        JOptionPane.showMessageDialog(ipHelperPanel, "DONE");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ipHelperPanel, "ERROR: " + ex.toString());
                    }
                }
            }
        });
    }
}
