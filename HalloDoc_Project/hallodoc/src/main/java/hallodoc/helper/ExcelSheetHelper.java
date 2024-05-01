package hallodoc.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Header;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import hallodoc.dto.NewRequestDataDto;
import hallodoc.dto.SearchRecordsDashboardData;

public class ExcelSheetHelper {

	public static String[] ALL_HEADERS = { "Name", "Date Of Birth", "Requestor", "Requested Date", "Physician Name",
			"Date of Service", "Phone Number", "Requestor Phone", "Request Type", "Address", "Notes" };

	public static String[] NEW_HEADERS = { "Name", "Date Of Birth", "Requestor", "Requested Date", "Phone Number",
			"Requestor Phone", "Request Type", "Address", "Notes"

	};

	public static String[] PENDING_ACTIVE_HEADERS = { "Name", "Date Of Birth", "Requestor", "Physician Name",
			"Date of Service", "Phone Number", "Requestor Phone", "Request Type", "Address", "Notes"

	};

	public static String[] CONCLUDE_HEADERS = { "Name", "Date Of Birth", "Physician Name", "Date of Service",
			"Phone Number", "Requestor Phone", "Request Type", "Address" };

	public static String[] TO_CLOSE_HEADERS = { "Name", "Date Of Birth", "Physician Name", "Date of Service", "Address",
			"Notes"

	};

	public static String[] UNPAID_HEADERS = { "Name", "Physician Name", "Date of Service", "Phone Number",
			"Requestor Phone", "Request Type", "Address" };

	public static String[] SEARCH_RECORDS_HEADERS = { "Patient Name", "Requestor", "Date Of Service", "Close Case Date",
			"Email", "Phone Number", "Address", "Zip", "Request Status", "Physician", "Admin Notes", "Patient Notes" };

	public static ByteArrayInputStream dataTOExcel(List<NewRequestDataDto> list, String currentStatus)
			throws IOException {

		Map<String, String[]> myMap = new HashMap<>();
		myMap.put("NEW", NEW_HEADERS);
		myMap.put("PENDING", PENDING_ACTIVE_HEADERS);
		myMap.put("ACTIVE", PENDING_ACTIVE_HEADERS);
		myMap.put("CONCLUDE", CONCLUDE_HEADERS);
		myMap.put("TO-CLOSE", TO_CLOSE_HEADERS);
		myMap.put("UNPAID", UNPAID_HEADERS);
		myMap.put("ALL", ALL_HEADERS);
		Workbook workbook = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			Sheet sheet = workbook.createSheet(currentStatus);

			Row row = sheet.createRow(0);

			for (int i = 0; i < myMap.get(currentStatus).length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(myMap.get(currentStatus)[i]);
			}

			int rowIndex = 1;

			for (NewRequestDataDto data : list) {
				int cellIndex = 0;
				Row dataRow = sheet.createRow(rowIndex);

				rowIndex++;

				dataRow.createCell(cellIndex++).setCellValue(data.getName());

				if (!currentStatus.equals("UNPAID")) {
					dataRow.createCell(cellIndex++)
							.setCellValue(data.getDay() + " " + data.getMonth() + "," + data.getYear());
				}

				if (currentStatus.equals("NEW") || currentStatus.equals("ACTIVE") || currentStatus.equals("PENDING")
						|| currentStatus.equals("ALL")) {
					dataRow.createCell(cellIndex++).setCellValue(data.getRequestor());
				}

				if (currentStatus.equals("NEW") || currentStatus.equals("ALL")) {
					dataRow.createCell(cellIndex++).setCellValue(data.getRequestedDate());
				}

				if (!currentStatus.equals("NEW")) {
					dataRow.createCell(cellIndex++).setCellValue(data.getPhysicianName());
					dataRow.createCell(cellIndex++).setCellValue(data.getDateOfService());
				}

				if (!currentStatus.equals("TO-CLOSE")) {
					dataRow.createCell(cellIndex++).setCellValue(data.getPtPhoneNumber());
					dataRow.createCell(cellIndex++).setCellValue(data.getReqPhoneNumber());
					dataRow.createCell(cellIndex++).setCellValue(data.getReqPhoneNumberType());
				}

				dataRow.createCell(cellIndex++).setCellValue(
						data.getStreet() + ", " + data.getCity() + ", " + data.getState() + ", " + data.getZipcode());

				if (!currentStatus.equals("UNPAID") || !currentStatus.equals("CONCLUDE")) {
					dataRow.createCell(cellIndex++).setCellValue(data.getNotes());
				}
			}

			workbook.write(out);

			return new ByteArrayInputStream(out.toByteArray());

		}

		catch (Exception e) {
			e.printStackTrace();
			 ;
			return null;
		} finally {
			workbook.close();
			out.close();
		}

	}
	
	public static ByteArrayInputStream downlaodSearchRecordData(List<SearchRecordsDashboardData> list)
			throws IOException {
		
		Workbook workbook = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {

			Sheet sheet = workbook.createSheet("Search Record Data");

			Row row = sheet.createRow(0);

			for (int i = 0; i < SEARCH_RECORDS_HEADERS.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(SEARCH_RECORDS_HEADERS[i]);
			}

			int rowIndex = 1;

			for (SearchRecordsDashboardData data : list) {
				int cellIndex = 0;
				Row dataRow = sheet.createRow(rowIndex);

				rowIndex++;

				dataRow.createCell(cellIndex++).setCellValue(data.getPatientName());
				dataRow.createCell(cellIndex++).setCellValue(data.getRequestorName());
				dataRow.createCell(cellIndex++).setCellValue(data.getDateOfService());
				dataRow.createCell(cellIndex++).setCellValue(data.getCloseCaseDate());
				dataRow.createCell(cellIndex++).setCellValue(data.getEmail());
				dataRow.createCell(cellIndex++).setCellValue(data.getPhoneNumber());
				dataRow.createCell(cellIndex++).setCellValue(data.getAddress());
				dataRow.createCell(cellIndex++).setCellValue(data.getZip());
				dataRow.createCell(cellIndex++).setCellValue(data.getRequestStatus());
				dataRow.createCell(cellIndex++).setCellValue(data.getPhysicianName());
				dataRow.createCell(cellIndex++).setCellValue(data.getPhysicianNote());
				dataRow.createCell(cellIndex++).setCellValue(data.getAdminNotes());
				dataRow.createCell(cellIndex++).setCellValue(data.getPatientNotes());
			
				

				
			}

			workbook.write(out);

			return new ByteArrayInputStream(out.toByteArray());

		}

		catch (Exception e) {
			e.printStackTrace();
			 ;
			return null;
		} finally {
			workbook.close();
			out.close();
		}
		
	}
	
	
}
