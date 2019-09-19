package com.nandaadisaputra.praktikum1mobileprogram.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.nandaadisaputra.praktikum1mobileprogram.R;
import com.nandaadisaputra.praktikum1mobileprogram.activity.FormPenjualanActivity;
import com.nandaadisaputra.praktikum1mobileprogram.activity.KalkulatorActivity;
import com.nandaadisaputra.praktikum1mobileprogram.activity.MainActivity;
import com.nandaadisaputra.praktikum1mobileprogram.model.Portofolio;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailProfilFragment extends Fragment implements ViewPagerEx.OnPageChangeListener {

    @BindView(R.id.sliderSlider)
    SliderLayout sliderSlider;
    @BindView(R.id.lihatnilai)
    Button lihatnilai;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.cv)
    TextView cv;
    private Unbinder unbinder;
    private Context context;

    public DetailProfilFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for thi fragment
        View view = inflater.inflate(R.layout.detail_profil_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        setupslider();


        lihatnilai.setOnClickListener(view2 -> {
            Intent i = new Intent(getActivity(), KalkulatorActivity.class);
            startActivity(i);
        });
        profileImage.setOnClickListener(view1 -> {
            Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sharingIntent.setData(Uri.parse("https://github.com/NandaAdisaputra"));
            getActivity().startActivity(sharingIntent);
        });
        cv.setOnClickListener(view1 -> {
            Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sharingIntent.setData(Uri.parse("https://drive.google.com/file/d/18ByQRZdxD-Udfk1xu5U-AvNaqxzpQrWj/view?usp=sharing"));
            getActivity().startActivity(sharingIntent);
        });
        return view;


    }


    private void initView(View view) {
    }

    private void setupslider() {
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Profil", R.drawable.nanda);
        file_maps.put("Portofolio", R.drawable.sertifikatandroidpemula);
        file_maps.put("Trainer Sandec", R.drawable.komunitas);
        file_maps.put("Proses Belajar", R.drawable.dicodingexpert);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());

            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            sliderSlider.addSlider(textSliderView);

        }

        sliderSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderSlider.setCustomAnimation(new DescriptionAnimation());
        sliderSlider.setDuration(4000);
        sliderSlider.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
