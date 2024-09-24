package core;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import java.text.Normalizer;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.testng.Assert;
import utilities.DateTime;

public class BasePage {
    private static final Logger logger = LogHelper.getLogger();
    protected KeywordWeb keyword;
    public BasePage() {
        keyword = new KeywordWeb();
    }
    public BasePage(KeywordWeb keyword) {
        this.keyword = keyword;
    }
    public PropertiesFile propertiesFile = new PropertiesFile();
    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String makeSlug(String input) {
        if (input == null)
            throw new IllegalArgumentException();
        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("_");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
