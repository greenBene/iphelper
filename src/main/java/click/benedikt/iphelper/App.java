package click.benedikt.iphelper;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import javax.swing.*;
import java.io.*;

/**
 * Hello world!
 */
public class App {

    public static final String PATH_TO_DB = "./data/GeoLite2-City.mmdb";
    private static final String FILE_NAME = "./data/Test.xlsx";
    public static final String LANG = "de";


    public static void main(String[] args) {

        JFrame frame = new JFrame("IP Helper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new gui().ipHelperPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    static void addCountryToExcel(String pathToFile, String firstCell) throws Exception {
        IPUtil util = new IPUtil();

        try (InputStream inp = new FileInputStream(pathToFile)) {

            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            CellReference cr = new CellReference(firstCell);

            int startRowInt = cr.getRow();
            int colInt = cr.getCol();
            Cell ip_cell;
            Row row;
            int i = startRowInt;
            do {
                System.out.println("firstCell: " + firstCell + " i: " + i);
                row = sheet.getRow(i);
                if (row != null) {
                    ip_cell = row.getCell(colInt);
                    Cell country_cell = row.createCell(colInt + 1);
                    String name = util.getCountry(ip_cell.getStringCellValue());
                    country_cell.setCellValue(name);
                }
                i++;
            } while (row != null);

            OutputStream fileOut = new FileOutputStream(pathToFile);
            wb.write(fileOut);
            wb.close();
        }
    }

}
