import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Launcher {

    public static void main( String[] args ) {
        List<String> files = new ArrayList<String>( 4 );
        files.add( "D:\\Repositories\\GetUniqueUsers\\CITDS2019_users.xls" );
        files.add( "D:\\Repositories\\GetUniqueUsers\\CITDS2017_users (1).xls" );
        files.add( "D:\\Repositories\\GetUniqueUsers\\CITDS2015_users (1).xls" );
        files.add( "D:\\Repositories\\GetUniqueUsers\\JCKBSE2014_users.xls" );

        List<Sheet> sheets = new ArrayList<Sheet>( 4 );

        for ( String file : files ) {
            sheets.add( readCucumberReportSheet( file ) );
        }

        List<User> mainUsers = new ArrayList<>( );
        mainUsers.add( new User( "Last name",	"First name",	"E-mail address",	"Organization",	"Country",	"Roles",	"Authored papers",	"Submitted papers",	"Assigned papers" ));
        mainUsers.addAll( readAllUsers( sheets.get( 0 ) ) );

        List<User> users1 = readAllUsers( sheets.get( 1 ) );
        List<User> users2 = readAllUsers( sheets.get( 2 ) );
        List<User> users3 = readAllUsers( sheets.get( 3 ) );

        for ( User user : users1 ) {

            if ( !mainUsers.contains( user ) ) {
                mainUsers.add( user );
            }
        }

        for ( User user : users2 ) {
            if ( !mainUsers.contains( user ) ) {
                mainUsers.add( user );
            }
        }

        for ( User user : users3 ) {
            if ( !mainUsers.contains( user ) ) {
                mainUsers.add( user );
            }
        }

        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet authors
                = workbook.createSheet( "Authors" );

        XSSFSheet members
                = workbook.createSheet( "MembersAndReviewers" );

        // creating a row object
        XSSFRow row = null;

        int rowid1 = 0;
        int rowid2 = 0;

        // writing the data into the sheets...

        for ( User user : mainUsers ) {
            Object[] objectArr = user.toArray();

            int cellid = 0;

            if ( objectArr[2] != null && objectArr[2].toString().length() > 0 ) {

                if (objectArr[5].toString().trim().contains( "PC_MEMBER" ) || objectArr[5].toString().trim().contains( "REVIEWER" ) || objectArr[5].toString().trim().contains( "PC_CHAIR" )){
                    row = members.createRow( rowid2++ );
                } else {
                    row = authors.createRow( rowid1++ );
                }

                if (row != null) {
                    for ( Object obj : objectArr ) {
                        Cell cell = row.createCell( cellid++ );
                        cell.setCellValue( (String) obj );
                    }
                }

            }
        }

        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(
                    new File( "D:/GFGsheet.xlsx" ) );
            workbook.write( out );
            out.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }



    public static Sheet readCucumberReportSheet( String pathToFile ) {
        Workbook workbook;
        Sheet sheet = null;

        try {
            workbook = WorkbookFactory.create( new File( pathToFile ) );
            sheet = workbook.getSheetAt( 0 );
        } catch ( Exception ex) {
            ex.printStackTrace();
        }

        return  sheet;
    }

    public static List<User> readAllUsers ( Sheet sheet ) {
        int sheetSize = sheet.getLastRowNum();
        List<User> users = new ArrayList<>();

        for ( int rowsNumber = 1; rowsNumber <= sheetSize; rowsNumber++ ) {

            Row row = sheet.getRow( rowsNumber );
            String lastName     = row.getCell( 0 ).getStringCellValue();
            String firstName    = row.getCell( 1 ).getStringCellValue();
            String email        = row.getCell( 2 ).getStringCellValue();
            String organization = row.getCell( 3 ).getStringCellValue();
            String country      = row.getCell( 4 ).getStringCellValue();
            String roles        = row.getCell( 5 ).getStringCellValue();
            String authPapers   = row.getCell( 6 ).getStringCellValue();
            String submPapers   = row.getCell( 7 ).getStringCellValue();
            String assiPapers   = row.getCell( 8 ).getStringCellValue();


            users.add( new User( lastName, firstName, email, organization, country, roles, authPapers, submPapers, assiPapers) );
        }


        return users;
    }

}

