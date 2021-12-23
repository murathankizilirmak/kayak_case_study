package TestCases.UI;

import Pages.MainPage;
import java.awt.*;
import java.io.IOException;

public class ProductBuy extends MainPage {
    public static void main(String[] args) throws IOException, AWTException {
        OpenPortal("Chrome");
        OpenTheMainPage();
        SearchRoundTripDestination("PAR","FRA");
        SearchRoundTripRange(1);
        ClickSearch();
    }

}