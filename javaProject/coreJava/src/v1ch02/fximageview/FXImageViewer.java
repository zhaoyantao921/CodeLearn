package v1ch02.fximageview;

import com.sun.glass.ui.CommonDialogs;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
* 看图程序
*
* */
public class FXImageViewer  extends Application {

    private static final int MIN_SIZE = 400;
    @Override
    public void start(Stage primaryStage) throws Exception {
        //画板
        BorderPane pane =new BorderPane();
        //新建菜单bar
        MenuBar menuBar = new MenuBar();
        //设置菜单bar
        pane.setTop(menuBar);
        //新建 文件菜单
        Menu fileMenu =new Menu("文件");
        //添加 文件菜单
        menuBar.getMenus().add(fileMenu);
        //菜单选择项 打开
        MenuItem menuOpne =new MenuItem("打开");
        //菜单选择项 退出
        MenuItem menuExit =new MenuItem("退出");
        //打开行为
        menuOpne.setOnAction(event -> load(primaryStage,pane));
        //关闭行为
        menuExit.setOnAction(event -> System.exit(0));
        //将菜单选项添加到菜单
        fileMenu.getItems().addAll(menuOpne,menuExit);
        //设置程序场景参数
        primaryStage.setScene(new Scene(pane,MIN_SIZE,MIN_SIZE));
        //设置程序标题
        primaryStage.setTitle("ImageViewer");
        //显示程序
        primaryStage.show();
    }

    private void load(Stage primaryStage, BorderPane pane) {
        //文件选择器
        FileChooser fileChooser =new FileChooser();
        //文件选择器 筛选器参数
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("图片","*.jpg","*.png","*.gif"),
                new FileChooser.ExtensionFilter("所有文件","*.*")
        );
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file!=null){
            try{
                Path path =file.toPath();
                javafx.scene.image.Image image = new Image(Files.newInputStream(path));
                pane.setCenter(new ImageView(image));
            }catch (IOException e){
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Cannot open file.");
                alert.showAndWait();
            }
        }
    }

    private MenuBar getBar(MenuBar bar) {
        return bar;
    }
}
