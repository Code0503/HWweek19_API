package com.restfullbooker;


import com.restfullbooker.testbase.TestBaseRestfullBooker;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by bhavesh
 */

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature",
        tags = "@SEPP,@SANITY,@REGRESSION,@REMOVE")// tags are optional, can run from runner class


public class CucumberRunner extends TestBaseRestfullBooker {

}
