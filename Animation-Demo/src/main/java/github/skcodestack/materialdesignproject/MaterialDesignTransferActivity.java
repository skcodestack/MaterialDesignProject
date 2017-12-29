package github.skcodestack.materialdesignproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import github.skcodestack.materialdesignproject.transfer.CommonTransferActivity;
import github.skcodestack.materialdesignproject.transfer.RecyclerViewTransferActivity;
import github.skcodestack.materialdesignproject.transfer.TransferDetailActivity;

public class MaterialDesignTransferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_material_design_transfer);


    }



    public void btn_common(View view){
        startActivity(new Intent(view.getContext(), CommonTransferActivity.class));
    }


    public void btn_recyclerview(View view){
        startActivity(new Intent(view.getContext(), RecyclerViewTransferActivity.class));
    }

}
