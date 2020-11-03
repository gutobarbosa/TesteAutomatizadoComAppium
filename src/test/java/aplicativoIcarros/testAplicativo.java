package aplicativoIcarros;

//import com.sun.org.apache.xpath.internal.operations.Bool;
//import gerarArquivo.gerandoArquivo;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.Assert.assertTrue;

public class testAplicativo {


    private AndroidDriver<MobileElement> driver; // private AndroidDriver<MobileElement> driver; talvez possa ser assim tbm

    @Before
    public void setUp() throws MalformedURLException {
        //Local

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        desiredCapabilities.setCapability("appPackage", "br.com.icarros.androidapp");
        desiredCapabilities.setCapability("appActivity", "br.com.icarros.androidapp.ui.home.MainActivity");
        desiredCapabilities.setCapability("plataformVersion", "9.0");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");//local
        driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void ConsultaCarros() throws IOException {
        MobileElement el3 = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/searchText");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/fullSearchText");
        el4.click();
        el4.sendKeys("Hyundai creta");
        MobileElement el5 = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/searchButton");
        el5.click();
        // Validando se o valor retornado é maior que 7 items
        String value;
        Integer price;
        Boolean result = false;
        MobileElement el6 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.TextView[1]");
        value = el6.getText();
        value = value.replace("Deals (", "");//removendo informações indesejadas
        value = value.replace(")", "");//removendo informações indesejadas
        value = value.replace(" ", "");//removendo espaços
        value = value.trim();//removendo espaços sobreçalentes
        price = Integer.parseInt(value);
        if(price > 7 ){
            result = true;
        }
        // verificando valores

        //item
        MobileElement valor1 = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/priceText");
        String valuePrice;
        valuePrice = valor1.getText();

        //verificando modelo
        Integer modelExist;
        String Models;
        Boolean response = false;
        MobileElement model1 = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/makeAndModelText");
        Models = model1.getText();
        modelExist = Models.indexOf("Creta");//removendo informações indesejadas
        if(modelExist != -1){
            response= true;
        }
/*
        MobileElement informations = (MobileElement) driver.findElementById("br.com.icarros.androidapp:id/dealList");
        String itensList;
        itensList = informations.getText();
        String fileDate = "content://com.android.providers.downloads.documents/document/msd%3A24";
        String demilit = ";";
        gerandoArquivo.escreverCSV(demilit,itensList,fileDate);
*/

        Assert.assertEquals("1436", value);
        Assert.assertEquals("79,890", valuePrice);
        assertTrue(result);// valida se retornou mais de 7 itens
        assertTrue(response);// valida se é model creta
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}


