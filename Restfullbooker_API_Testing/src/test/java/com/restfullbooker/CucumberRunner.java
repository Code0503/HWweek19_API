package com.restfullbooker;


import com.restfullbooker.testbase.TestBaseRestfullBooker;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;



@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty"},
        features = "src/test/java/resources/feature",
        tags = "@SEPP,@SANITY,@REGRESSION,@REMOVE")// tags are optional, can run from runner class


public class CucumberRunner extends TestBaseRestfullBooker {

}
