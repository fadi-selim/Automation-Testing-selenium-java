package com.company;
import org.testng.*;
import org.testng.xml.XmlSuite;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CostumReport implements IReporter {



    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> list1, String s) {

        String passedcount ="" ,failedcount ="",skippedcount ="" ;
        List<List<String>> ReportDetails = new ArrayList<>(0);


        for (ISuite suiteobj : list1)
        {

            Map<String , ISuiteResult> suiteResultMap = suiteobj.getResults();
            for (ISuiteResult suiteresultobj : suiteResultMap.values())
            {
                System.out.println( "Passed Tests" +suiteresultobj.getTestContext().getPassedTests().getAllResults().size());
                System.out.println( "Failed Tests" +suiteresultobj.getTestContext().getFailedTests().getAllResults().size());
                System.out.println( "Skipped Tests" +suiteresultobj.getTestContext().getSkippedTests().getAllResults().size());

                passedcount = String.valueOf(suiteresultobj.getTestContext().getPassedTests().getAllResults().size());
                failedcount = String.valueOf(suiteresultobj.getTestContext().getFailedTests().getAllResults().size());
                skippedcount = String.valueOf(suiteresultobj.getTestContext().getSkippedTests().getAllResults().size());

            }
        }


        for (ISuite suiteobj : list1)
        {

            Map<String , ISuiteResult> suiteResultMap = suiteobj.getResults();
            ////////////////////////////////FOR PASSED//////////////////////
            for (ISuiteResult suiteresultobj : suiteResultMap.values())
            {
             Set<ITestResult> RList = suiteresultobj.getTestContext().getPassedTests().getAllResults();

                     for (ITestResult x : RList)
                     {
                         List<String> L1 = new ArrayList<String>();

                         L1.add(x.getTestClass().getName());
                         L1.add(x.getName());
                         L1.add("Passed");L1.add("");L1.add("");
                         L1.add(String.valueOf(x.getMethod().getInvocationCount()));

                         String reportmsg = Reporter.getOutput(x).size()>0?Reporter.getOutput(x).get(0):"";
                         String smesage = x.getName() + x.getTestClass().getName()+reportmsg;
                        // System.out.println(smesage+String.valueOf(x.getMethod().getInvocationCount()));
                         //ClassName	MethodName	Status	Reason of Failure	Attachement	# of Invocation
                         ReportDetails.add(L1);
                     }


            }
            ////////////////////////////////FOR FAILED//////////////////////
            for (ISuiteResult suiteresultobj : suiteResultMap.values())
            {
                Set<ITestResult> RList = suiteresultobj.getTestContext().getFailedTests().getAllResults();

                for (ITestResult x : RList)
                {
                    List<String> L1 = new ArrayList<String>();
                    String reportmsg = Reporter.getOutput(x).size()>1&&(!Reporter.getOutput(x).get(0).equals(Reporter.getOutput(x).get(1)))?Reporter.getOutput(x).get(0)+Reporter.getOutput(x).get(1):Reporter.getOutput(x).size()>0?Reporter.getOutput(x).get(0):"";
                    String scpath = System.getProperty("user.dir") + "/ErrorSc/" + x.getName() + ".png";

                    L1.add(x.getTestClass().getName()); //0
                    L1.add(x.getName()); //1
                    L1.add("Failed"); //2
                    L1.add(reportmsg); //3
                    L1.add(scpath); //4
                    L1.add(String.valueOf(x.getMethod().getInvocationCount()));


                    String smesage = x.getName() + x.getTestClass().getName()+reportmsg;
                    System.out.println(smesage+String.valueOf(x.getMethod().getInvocationCount()));
                    ReportDetails.add(L1);
                }


            }

            ////////////////////////////////FOR SKIPPED//////////////////////
            for (ISuiteResult suiteresultobj : suiteResultMap.values())
            {
                Set<ITestResult> RList = suiteresultobj.getTestContext().getSkippedTests().getAllResults();

                for (ITestResult x : RList)
                {
                    List<String> L1 = new ArrayList<String>();
                    String reportmsg = Reporter.getOutput(x).size()>0?Reporter.getOutput(x).get(0):"";
                    String smesage = x.getName() + x.getTestClass().getName()+reportmsg;
                    System.out.println(smesage+String.valueOf(x.getMethod().getInvocationCount()));
                    L1.add(x.getTestClass().getName());
                    L1.add(x.getName());
                    L1.add("Skipped");L1.add("");L1.add("");
                    L1.add(String.valueOf(x.getMethod().getInvocationCount()));
                    ReportDetails.add(L1);
                }

                //ReportDetails.add(L1);
            }


        }


        String resultfile =  System.getProperty("user.dir") + "/CustomReport/" + "/CustomReport.html/";
        File file = new File(resultfile);

        file.delete();
        if (!file.exists()) {
            try {
                FileWriter fw = new FileWriter(file.getAbsoluteFile() ,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("<!DOCTYPE html>");
                bw.write("<html>");
                bw.write("<head>");
                bw.write("<style>");
                bw.write("table, th, td {");
                bw.write("  border: 2px solid black;");
                bw.write("  border-collapse: collapse;");
                bw.write("  text-align: center;");
                bw.write("}");
                bw.write("th, td {  padding: 5px;}");
                bw.write("th {  text-align: center;}");
                bw.write(".failed td {	background-color: #F33}");
                bw.write(".skipped td {    background-color: #CCC}");
                bw.write(".passed td {	background-color: #0A0}");
                bw.write("</style>");
                bw.write("</head>");
                bw.write("<body>");
                bw.write("");
                bw.write("<h2 style=\"text-align:center;\">Tests Result</h2>");
                bw.write("<h3 style=\"text-align:center;\">Summary</h3>");
                bw.write("<table style=\"width:80%;margin-left:auto;margin-right:auto;\">");
                bw.write("  <tr>");
                bw.write("    <th>Passed</th>");
                bw.write("    <th>Failed</th> ");
                bw.write("    <th>Skipped</th>");
                bw.write("  </tr>");
                bw.write("  <tr>");
                bw.write("    <td>"+passedcount+"</td>");
                bw.write("    <td>"+failedcount+"</td>");
                bw.write("    <td>"+skippedcount+"</td>");
                bw.write("  </tr>");
                bw.write("</table>");
                bw.write("<h3 style=\"text-align:center;\">Details</h3>");
                bw.write("");
                bw.write(" <table style=\"width:80%;margin-left:auto;margin-right:auto;\">");
                bw.write("  <tr>");
                bw.write("    <th>ClassName</th>");
                bw.write("    <th>MethodName</th>");
                bw.write("    <th>Status</th>");
                bw.write("    <th>Reason of Failure</th>");
                bw.write("    <th>Attachement</th>");
                bw.write("    <th># of Invocation</th>");
                bw.write("  </tr>");

                for (List<String>  RL: ReportDetails)
                {
                    bw.write("  <tr ");
                    bw.write(RL.get(2)=="Passed"?" class=\"passed\" ":RL.get(2)=="Failed"?" class=\"failed\" ":RL.get(2)=="Skipped"?" class=\"skipped\"":"");
                    bw.write(">");
                    bw.write("    <td>"+RL.get(0)+"</td>");
                    bw.write("    <td>"+RL.get(1)+"</td>");
                    bw.write("    <td>"+RL.get(2)+"</td>");
                    bw.write("    <td>"+RL.get(3)+"</td>");

                    bw.write(RL.get(2)=="Failed"?"	<td><p><a href="+RL.get(4)+">ScreenShot</a></p></td>":"<td>"+" "+"</td>");

                    bw.write("    <td>"+RL.get(5)+"</td>");
                    bw.write("  </tr>");
                }

                bw.write("");
                bw.write("</table>");
                bw.write("</body>");
                bw.write("</html>");



                bw.flush();
                bw.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String cwd = System.getProperty("user.dir");
        String rs = cwd.replace("\\","\\\\");
        rs+="\\CustomReport\\CustomReport.html";
        File htmlFile = new File(rs);
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
