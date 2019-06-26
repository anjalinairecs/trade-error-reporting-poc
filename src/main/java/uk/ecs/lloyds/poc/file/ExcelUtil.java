package uk.ecs.lloyds.poc.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.ecs.lloyds.poc.exception.TradeErrorReportingException;

public class ExcelUtil {

	public static boolean write(ResultSet resultset, String fileName) throws TradeErrorReportingException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Output");

		try {
			int rowCount = addHeadings(sheet, resultset);
			rowCount = addData(rowCount, sheet, resultset);

			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
		} catch (IOException e) {
			throw new TradeErrorReportingException("Error while writing to excel file : ", e);
		}
		return true;
	}

	private static int addData(int rowCount, XSSFSheet sheet, ResultSet resultset) throws TradeErrorReportingException{

		try {
			ResultSetMetaData metadata = resultset.getMetaData();
			int noOfColumns = metadata.getColumnCount();

			while (resultset.next()) {
				Row row = sheet.createRow(rowCount++);
				for (int i = 0; i < noOfColumns; i++) {
					Cell cell = row.createCell(i);
					cell.setCellValue(resultset.getString(i + 1));
				}
			}
		} catch (SQLException e) {
			throw new TradeErrorReportingException("Error while writing to excel file : Writing resultset : ", e);
		}
		return rowCount;
	}

	private static int addHeadings(XSSFSheet sheet, ResultSet resultset) throws TradeErrorReportingException{
		int rowCount;
		try {
			ResultSetMetaData metadata = resultset.getMetaData();
			int noOfColumns = metadata.getColumnCount();

			rowCount = 0;
			Row row = sheet.createRow(rowCount++);
			CellStyle heading = getCellStyleHeading(sheet);

			for (int i = 0; i < noOfColumns; i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(heading);
				String columnName = metadata.getColumnName(i + 1);
				cell.setCellValue(columnName);
			}
		} catch (SQLException e) {
			throw new TradeErrorReportingException("Error while writing to excel file : Adding headings : ", e);
		}
		return rowCount;
	}

	private static CellStyle getCellStyleHeading(XSSFSheet sheet) {
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();

		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		Font font = sheet.getWorkbook().createFont();
		font.setBoldweight((short) 2);
		font.setFontHeightInPoints((short) 14);
		cellStyle.setFont(font);
		cellStyle.setWrapText(true);

		return cellStyle;
	}

}
