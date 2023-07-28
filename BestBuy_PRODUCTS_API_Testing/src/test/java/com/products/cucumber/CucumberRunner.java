package com.products.cucumber;


import com.products.cucumber.testbase.TestBaseBestBuy;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature",
        tags = "@SMOKE,@SANITY,@REGRESSION,@INTERESTING,@DELETE")// tags are optional, can run from runner class


public class CucumberRunner extends TestBaseBestBuy {

}
