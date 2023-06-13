package report;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CustomReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio/custom-report.html"))) {
            writer.write("<html><head><title>Relatório de Teste</title></head><body>");
            writer.write("<h1>Relatório de Teste</h1>");

            for (ISuite suite : suites) {
                writer.write("<h2>" + suite.getName() + "</h2>");

                for (ISuiteResult result : suite.getResults().values()) {
                    ITestContext context = result.getTestContext();

                    writer.write("<h3>Passed: " + context.getPassedTests().size() + "</h3>");
                    for (ITestResult testResult : context.getPassedTests().getAllResults()) {
                        writer.write(testResult.getMethod().getMethodName() + "<br>");
                    }

                    writer.write("<h3>Failed: " + context.getFailedTests().size() + "</h3>");
                    for (ITestResult testResult : context.getFailedTests().getAllResults()) {
                        writer.write(testResult.getMethod().getMethodName() + "<br>");
                    }

                    writer.write("<h3>Skipped: " + context.getSkippedTests().size() + "</h3>");
                    for (ITestResult testResult : context.getSkippedTests().getAllResults()) {
                        writer.write(testResult.getMethod().getMethodName() + "<br>");
                    }
                }
            }

            writer.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
