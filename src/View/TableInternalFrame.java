package View;

import Objects.Country;
import Utilites.GlobalStates;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TableInternalFrame extends JFrame {

    DefaultTableModel allData;
    List<Country> allCountries = new ArrayList<>(GlobalStates.INSTANCE.getSortedMapOfcountries().values());
    JTable tableOfAllCountries = new JTable();

    public TableInternalFrame(){
        allData = new DefaultTableModel();
        setLayout(null);
        setSize(new Dimension(500, 500));
        for (Country country : allCountries){
            Vector<String> column = new Vector<>();
            column.add(country.getName());
            column.add(Long.toString(country.getPopulation()));
            column.add(Long.toString(country.getInfectedPopulation()));
            System.out.println(column);
            allData.addRow(column);
        }
        allData.setColumnIdentifiers(new String[] {"NAME", "POPULATION", "INFECTED POPULATION"});
        tableOfAllCountries.setModel(allData);
        tableOfAllCountries.setPreferredScrollableViewportSize(new Dimension(300, 300));
        tableOfAllCountries.setFillsViewportHeight(true);
        tableOfAllCountries.setVisible(true);
        tableOfAllCountries.revalidate();
        JScrollPane scrollableTable = new JScrollPane(tableOfAllCountries);
        scrollableTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableTable.setBounds(1,1,500,300);
        scrollableTable.setVisible(true);
        getContentPane().add(scrollableTable);
        tableOfAllCountries.setSize(new Dimension(500, 500));
        pack();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300,300);
    }

}
