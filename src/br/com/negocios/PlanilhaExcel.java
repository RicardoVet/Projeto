/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.negocios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ricardo
 */
public class PlanilhaExcel {
    
    public List<Cell> ler(String caminho) throws FileNotFoundException, IOException{
        File file = new File(caminho);
        FileInputStream arquivoPlanilha = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(arquivoPlanilha);
        Iterator<XSSFSheet> sheetIterator = workbook.iterator();
        List<Sheet> planilhas = new LinkedList<>();
        List<Row> linhas = new LinkedList<>();
        List<Cell>celulas = new LinkedList<>();
        while(sheetIterator.hasNext()){
            planilhas.add(sheetIterator.next());
        }
        
        for(Sheet planilha:planilhas){
        	Iterator<Row> row = planilha.rowIterator();
        	while(row.hasNext()){
        		linhas.add(row.next());
        	}
        }
        
        for( Row linha:linhas){
        	Iterator<Cell> cell = linha.iterator();
        	while(cell.hasNext()){
        		celulas.add(cell.next());
        	}
        }
        System.out.println("numero de planilhas "+planilhas.size());
        System.out.println("numero de linhas "+linhas.size());
        System.out.println("numero de celulas "+celulas.size());
        
        
        for(Cell celula:celulas){
        	System.out.println(celula.getSheet().getSheetName());
        	System.out.println("linha:"+celula.getRowIndex()+1+" coluna:"+celula.getColumnIndex());
        	int tipo = celula.getCellType();
        	switch (tipo) {
			case Cell.CELL_TYPE_STRING:
				System.out.println(celula.getStringCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:
				System.out.println(celula.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				System.out.println(celula.getCellFormula());
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				System.out.println(celula.getBooleanCellValue());
				break;
			default:
				break;
			}
        }
        return celulas;
    }
}
