package com.example.player.video;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ListViewItem> gridItems = new ArrayList<ListViewItem>();
    private  RecyclerView recyclerView;
    private ListViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Utils.printIfDebug("Permission", "granted\t" + response.getPermissionName());
                        setGridAdapter("/storage/emulated/0/movies");

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();

    }

    private void setGridAdapter(String path) {
        Utils.printIfDebug("list","list");

         recyclerView = findViewById(R.id.folder_list_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListViewAdapter(createGridItems(path));
        recyclerView.setAdapter(mAdapter);


    }


    /**
     * Go through the specified directory, and create items to display in our
     * GridView
     */
    private List<ListViewItem> createGridItems(String directoryPath) {
        List<ListViewItem> items = new ArrayList<ListViewItem>();

        // List all the items within the folder.
        File[] files = new File(directoryPath).listFiles(new ImageFileFilter());
        if (files != null) {
            for (File file : files) {

                // Add the directories containing images or sub-directories
                if (file.isDirectory()
                        && file.listFiles(new ImageFileFilter()).length > 0) {

                    items.add(new ListViewItem(file.getAbsolutePath(), true));
                }
                // Add the images
                else {

                    items.add(new ListViewItem(file.getAbsolutePath(), false));
                }
            }
        }

        return items;
    }


    /**
     * Checks the file to see if it has a compatible extension.
     */
    private boolean isImageFile(String filePath) {
        // Add other formats as desired
        return filePath.endsWith(".mp4") || filePath.endsWith(".mp4");
    }


    private class ImageFileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            } else if (isImageFile(file.getAbsolutePath())) {
                Utils.printIfDebug("path",file.getAbsolutePath());
                return true;
            }
            return false;
        }
    }
}
