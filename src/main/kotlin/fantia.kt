import com.github.kittinunf.fuel.Fuel

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.File
import java.util.concurrent.TimeUnit
import org.openqa.selenium.JavascriptExecutor
import java.nio.file.Paths
import java.util.*

fun main(args: Array<String>) = runBlocking{

    val input =Scanner(System.`in`)
    //ログイン時のページのurl 例->"https://id.fantia.jp/auth/?response_type=code&client_id=EUtcltNGEX&state=0uFwPvlYlSHp8KjAXy&redirect_uri=https://fantia.jp/auth/toranoana/callback"
    val login_url ="https://id.fantia.jp/auth/?response_type=code&client_id=EUtcltNGEX&state=0uFwPvlYlSHp8KjAXy&redirect_uri=https://fantia.jp/auth/toranoana/callback"
    print("mail address:")
    val mailadress=input.next()
    print("password:")
    val password =input.next()
    print("post_id:")
    val post_num =input.next()
    if(post_num.toIntOrNull()==null)throw NumberFormatException("${post_num} is not number")
    val post_prefix ="https://fantia.jp/posts/"
    val post_url=post_prefix+post_num
    print("directory name:")
    val dir =input.next()
    input.close()

    val options = ChromeOptions()
    options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome")

    val desiredCapabilities = DesiredCapabilities(mapOf("pageLoadStrategy" to "none"))
    desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options)
    val driver = ChromeDriver(desiredCapabilities)

    //遅延は各自の処理速度に依存するので変更してください

    try {

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS)
        driver.get(login_url)

        WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("input-mail")))
        println("set account info")
        val mail = driver.findElementById("input-mail")
        val pass = driver.findElementById("input-pass")
        mail.sendKeys(mailadress)
        pass.sendKeys(password)

        val login_btn = driver.findElementByCssSelector("#main > div.panel.panel-default > form > button")
        login_btn.click()
        delay(5000)
        println("login complete")

        driver.executeScript("window.stop();")
        driver.get(post_url)

        println("wait until page loaded")
        WebDriverWait(driver, 100).until { webDriver -> (webDriver as JavascriptExecutor).executeScript("return document.readyState") == "complete" }
        println("image loading need more wait(for ajax)")
        delay(3000)

        println("click 大きく表示")//大きく表示のボタンを押して元ソースのurlを取得
        driver.findElementByXPath("//*[@id=\"main\"]/div[2]/div/div/div/div/div[2]/div[3]/div/div[1]/div/post-content/div/div[2]/div[1]/div[3]/ul/li/div/div/div[1]/div/div/button[2]")
                .click()
        delay(3000)
        println("page loaded start click")
        //class name img-fluidに画像urlが存在しimg-fluidにはUI画像なども含まれているのでそれらは除く
        val downloadLinks = mutableListOf<String>()
        val links = driver.findElementsByClassName("img-fluid")

        for (i in links) {
            val attr: String? = i.getAttribute("ng-src")
            if (attr != null) {
                println("img-url:${attr}")
                if (attr.contains("http"))
                    downloadLinks.add(attr)
            }
        }
        val request = launch {
            for (i in downloadLinks) {
                launch(kotlin.coroutines.experimental.coroutineContext) {
                    downloadImg(i,dir)
                }
            }
        }
        println("wait downloading")
        request.join()


        /**
         * ↓　並べて表示のサムネイルをクリックして拡大した画像を取得しようとするあまりにも無謀な処理
         */
//    for (i in links){
//        val attr =i.getAttribute("ng-click")
//        if(attr.isNotEmpty()&&i.text.isEmpty()){
//            println(i.toString()+i.text)
//            i.click()
//            println("click thumbnail")
//            Thread.sleep(4000)
//            val v=WebDriverWait(driver,10).until { ExpectedConditions.visibilityOfElementLocated(
//                    By.cssSelector("#image-slideshow > div > div > img")
//            ) }
//
//            val imgsrc=driver.findElementByCssSelector("#image-slideshow > div > div > img")
//                    .getAttribute("ng-src")
//            println("large img:${imgsrc}")
//            downloadLinks.add(imgsrc)
//            val request= launch {
//                downloadImg(imgsrc)
//            }
//            request.join()
//
//        }
//        }
    }catch (e:Exception){
        e.printStackTrace()
    }finally {
        driver.quit()
    }

}

private  suspend fun downloadImg(url:String,dir:String="./"){
    val isDirExist = lazy { File(dir).exists() }.value
    if(isDirExist!=true)File(dir).mkdir()
    println("download url:${url}")
    val wait=Fuel.download(url).destination { response, url ->
        val fileName=Paths.get(url.toURI().path).fileName.toString()
        File(dir+'/'+fileName)
    }.awaitByteArrayResponse()
}