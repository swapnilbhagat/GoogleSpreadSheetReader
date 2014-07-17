/**
 * 
 */
package com.test.Reader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.TextContent;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetFeed;
import com.google.gdata.util.ServiceException;

/**
 * Reads the Google URL and prints entries.
 * 
 * @author SWAPNIL
 * 
 */
public class GoogleSpreadSheetReader {

	public static final String USERNAME = "xxx@gmail.com";
	public static final String PASSWORD = "xxx";
	public static final String URL = "https://spreadsheets.google.com/feeds/worksheets/tj6WP6oBgQ6azMLJ7D7QfvA/private/full";

	public static void main(String[] args) throws IOException, ServiceException {
		SpreadsheetService service = new SpreadsheetService(
				"Google SpreadSheet Reader");
		service.setUserCredentials(USERNAME, PASSWORD);
		URL spreadSheetURL = new URL(URL);
		SpreadsheetFeed spreadsheetFeed = service.getFeed(spreadSheetURL,
				SpreadsheetFeed.class);
		for (SpreadsheetEntry spreadsheetEntry : spreadsheetFeed.getEntries()) {
			URL worksheetURL = spreadsheetEntry.getWorksheetFeedUrl();
			WorksheetFeed worksheetFeed = service.getFeed(worksheetURL,
					WorksheetFeed.class);
			List<WorksheetEntry> worksheetEntries = worksheetFeed.getEntries();
			for (WorksheetEntry worksheetEntry : worksheetEntries) {
				TextContent textContent = (TextContent) worksheetEntry
						.getContent();
				String text = textContent.getContent().getPlainText();
				System.out.println(text);
			}
		}
	}

}
