package com.example.currency.googleapi;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;

import com.google.api.services.analyticsreporting.v4.model.ColumnHeader;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.DateRangeValues;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.MetricHeaderEntry;
import com.google.api.services.analyticsreporting.v4.model.Report;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;
import com.google.api.services.analyticsreporting.v4.model.ReportRow;

public class HelloAnalyticsReporting {
    private static final String APPLICATION_NAME = "Hello Analytics Reporting";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String KEY_FILE_LOCATION = "gakey.json";
    private static final String VIEW_ID = "177317931";

    public static void main(String[] args) {
        getResultsFromGA();
    }

    private static void getResultsFromGA(){
        try {
            AnalyticsReporting service = initializeAnalyticsReporting();

            GetReportsResponse response = getReport(service, "2018-02-01", "today");
            printResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getResultsFromParametrizedGA(){
        try {
            AnalyticsReporting service = initializeAnalyticsReporting();

            Metric sessionsMetric = new Metric().setExpression("ga:sessions").setAlias("sessions");
            Metric brateMetric = new Metric().setExpression("ga:bounceRate").setAlias("bounceRate");

            List<Metric> metricsToRequest = new ArrayList<>();
            metricsToRequest.add(sessionsMetric);
            metricsToRequest.add(brateMetric);

            Dimension pageTitleDimension = new Dimension().setName("ga:pageTitle");
            Dimension sourceDimension = new Dimension().setName("ga:source");

            List<Dimension> dimensionsToRequest = new ArrayList<>();
            dimensionsToRequest.add(pageTitleDimension);
            dimensionsToRequest.add(sourceDimension);


            GetReportsResponse response = getParametrizedReport(service, "2018-02-01", "today", metricsToRequest, dimensionsToRequest);
            printResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredential credential = GoogleCredential
                .fromStream(new FileInputStream(KEY_FILE_LOCATION))
                .createScoped(AnalyticsReportingScopes.all());

        // Construct the Analytics Reporting service object.
        return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();
    }

    private static GetReportsResponse getReport(AnalyticsReporting service, String startDate, String endDate) throws IOException {
        // Create the DateRange object.
        DateRange dateRange = new DateRange();
        dateRange.setStartDate(startDate);
        dateRange.setEndDate(endDate);

        // Create the Metrics object.
        Metric sessionsMetric = new Metric().setExpression("ga:sessions").setAlias("sessions");
        Metric brateMetric = new Metric().setExpression("ga:bounceRate").setAlias("bounceRate");

        List<Metric> metricsToRequest = new ArrayList<>();
        metricsToRequest.add(sessionsMetric);
        metricsToRequest.add(brateMetric);

        Dimension pageTitleDimension = new Dimension().setName("ga:pageTitle");
        Dimension sourceDimension = new Dimension().setName("ga:source");

        List<Dimension> dimensionsToRequest = new ArrayList<>();
        dimensionsToRequest.add(pageTitleDimension);
        dimensionsToRequest.add(sourceDimension);

        // Create the ReportRequest object.
        ReportRequest request = new ReportRequest()
                .setViewId(VIEW_ID)
                .setDateRanges(Arrays.asList(dateRange))
                .setMetrics(metricsToRequest)
                .setDimensions(dimensionsToRequest);

        ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
        requests.add(request);

        // Create the GetReportsRequest object.
        GetReportsRequest getReport = new GetReportsRequest()
                .setReportRequests(requests);

        // Call the batchGet method.
        GetReportsResponse response = service.reports().batchGet(getReport).execute();

        // Return the response.
        return response;
    }

    private static GetReportsResponse getParametrizedReport(AnalyticsReporting service, String startDate, String endDate, List<Metric> metrics, List<Dimension> dimensions) throws IOException {
        // Create the DateRange object.
        DateRange dateRange = new DateRange();
        dateRange.setStartDate(startDate);
        dateRange.setEndDate(endDate);

        // Create the ReportRequest object.
        ReportRequest request = new ReportRequest()
                .setViewId(VIEW_ID)
                .setDateRanges(Arrays.asList(dateRange))
                .setMetrics(metrics)
                .setDimensions(dimensions);

        ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
        requests.add(request);

        // Create the GetReportsRequest object.
        GetReportsRequest getReport = new GetReportsRequest()
                .setReportRequests(requests);

        // Call the batchGet method.
        GetReportsResponse response = service.reports().batchGet(getReport).execute();

        // Return the response.
        return response;
    }

    private static void printResponse(GetReportsResponse response) {

        for (Report report : response.getReports()) {
            ColumnHeader header = report.getColumnHeader();
            List<String> dimensionHeaders = header.getDimensions();
            List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
            List<ReportRow> rows = report.getData().getRows();

            if (rows == null) {
                System.out.println("No data found for " + VIEW_ID);
                return;
            }

            for (ReportRow row : rows) {
                List<String> dimensions = row.getDimensions();
                List<DateRangeValues> metrics = row.getMetrics();

                for (int i = 0; i < dimensionHeaders.size() && i < dimensions.size(); i++) {
                    System.out.println(dimensionHeaders.get(i) + ": " + dimensions.get(i));
                }

                for (int j = 0; j < metrics.size(); j++) {
                    System.out.print("Date Range (" + j + "): ");
                    DateRangeValues values = metrics.get(j);
                    for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
                        System.out.println(metricHeaders.get(k).getName() + ": " + values.getValues().get(k));
                    }
                }
            }
        }
    }
}