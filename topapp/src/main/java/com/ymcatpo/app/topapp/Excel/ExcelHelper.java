package com.ymcatpo.app.topapp.Excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { 
			"studentRollNo", 
			"studentFullName",
			"studentFatherName",
			"studentMotherName",
			"studentDob",
			"studentGender",
			"studentEmail", 
			"studentContactNo",
			"studentAddress",
			"studentZipCode",
			"studentState",
			"studentCity",
			"studentEducationId",
			"studentEducationSscMarks",
			"studentEducationSscPassYear",
			"studentEducationHsscMarks",
			"studentEducationHsscStream",
			"studentEducationHsscPasYear",
			"studentEducationGradCourse",
			"studentEducationGradsCgpa", 
			"studentEducationGradPassYear",
			"studentEducationPgCourse",
			"studentEducationPgCgpa",
			"studentEducationPgPassYear",
			"studentEducationYear",
			"studentEducationGapReason",
			"studentcertificationId",
			"studentcertificationOrgiDetails",
			"studentCertificationTitle",
			"studentcertificationIssueDate"
			};
	
	public static ByteArrayInputStream tutorialsToExcel(List<ExcelPojo> student ) {

	    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	      Sheet sheet = workbook.createSheet();

	      // Header
	      Row headerRow = sheet.createRow(0);

	      for (int col = 0; col < HEADERs.length; col++) {
	        Cell cell = headerRow.createCell(col);
	        cell.setCellValue(HEADERs[col]);
	      }

	      int rowIdx = 1;
	      for (ExcelPojo tutorial : student) {
	        Row row = sheet.createRow(rowIdx++);

	        row.createCell(0).setCellValue(tutorial.getStudentRollNo());
	        row.createCell(1).setCellValue(tutorial.getStudentFullName());
	        row.createCell(2).setCellValue(tutorial.getStudentFatherName());
	        row.createCell(3).setCellValue(tutorial.getStudentMotherName());
	        row.createCell(4).setCellValue(tutorial.getStudentDob());
	        row.createCell(5).setCellValue(tutorial.getStudentGender());
	        row.createCell(6).setCellValue(tutorial.getStudentEmail());
	        row.createCell(7).setCellValue(tutorial.getStudentContactNo());
	        row.createCell(8).setCellValue(tutorial.getStudentAddress());
	        row.createCell(9).setCellValue(tutorial.getStudentZipCode());
	        row.createCell(10).setCellValue(tutorial.getStudentState());
	        row.createCell(11).setCellValue(tutorial.getStudentCcity());
	        row.createCell(12).setCellValue(tutorial.getStudentEducationId());
	        row.createCell(13).setCellValue(tutorial.getStudentEducationSscMarks());
	        row.createCell(14).setCellValue(tutorial.getStudentEducationSscPassYear());
	        row.createCell(15).setCellValue(tutorial.getStudentEducationHsscMarks());
	        row.createCell(16).setCellValue(tutorial.getStudentEducationHsscStream());
	        row.createCell(17).setCellValue(tutorial.getStudentEducationHsscPasYear());
	        row.createCell(18).setCellValue(tutorial.getStudentEducationGradCourse());
	        row.createCell(19).setCellValue(tutorial.getStudentEducationGradsCgpa());
	        row.createCell(20).setCellValue(tutorial.getStudentEducationGradPassYear());
	        row.createCell(21).setCellValue(tutorial.getStudentEducationPgCourse());
	        row.createCell(22).setCellValue(tutorial.getStudentEducationPgCgpa());
	        row.createCell(23).setCellValue(tutorial.getStudentEducationPgPassYear());
	        row.createCell(24).setCellValue(tutorial.getStudentEducationYear());
	        row.createCell(25).setCellValue(tutorial.getStudentEducationGapReason());
	        row.createCell(26).setCellValue(tutorial.getStudentcertificationId());
	        row.createCell(27).setCellValue(tutorial.getStudentcertificationOrgiDetails());
	        row.createCell(28).setCellValue(tutorial.getStudentcertificationTitle());
	        row.createCell(29).setCellValue(tutorial.getStudentcertificationIssueDate());
	      }

	      workbook.write(out);
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
	    }
	  }
	
}
