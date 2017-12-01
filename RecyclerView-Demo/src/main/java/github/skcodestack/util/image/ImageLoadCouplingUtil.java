package github.skcodestack.util.image;

import java.io.File;

import com.bumptech.glide.Glide;

import android.widget.ImageView;

import github.skcodestack.util.GeneralUtil;
import github.skcodestack.util.image.glide.GlideCircleTransform;


public class ImageLoadCouplingUtil {
	private static ImageLoadCouplingUtil mInstance;

	private static int mErrorResouce;

	public static void initErrorResouce(int errorResouce) {
		mErrorResouce = errorResouce;
	}

	private ImageLoadCouplingUtil() {
		
	}

	public static ImageLoadCouplingUtil getInstance() {
		if (mInstance == null) {
			synchronized (ImageLoadCouplingUtil.class) {
				if (mInstance == null) {
					mInstance = new ImageLoadCouplingUtil();
				}
			}
		}
		return mInstance;
	}

	/************************** 本地文件圆形图片加载 ****************************/
	public void loadCircleImage(File file, ImageView imageView) {
		Glide.with(imageView.getContext()).load(file)
				.transform(new GlideCircleTransform(imageView.getContext()))
				.error(mErrorResouce).into(imageView);
	}

	public void loadCircleImage(File file, ImageView imageView, int errorResouce) {
		Glide.with(imageView.getContext()).load(file)
				.transform(new GlideCircleTransform(imageView.getContext()))
				.error(errorResouce).into(imageView);
	}

	/************************** 本地资源图片加载 ****************************/
	public void loadCircleImage(int resouceId, ImageView imageView) {
		Glide.with(imageView.getContext()).load(resouceId)
				.transform(new GlideCircleTransform(imageView.getContext()))
				.error(mErrorResouce).into(imageView);
	}

	public void loadCircleImage(int resouceId, ImageView imageView,
			int errorResouce) {
		Glide.with(imageView.getContext()).load(resouceId)
				.transform(new GlideCircleTransform(imageView.getContext()))
				.error(errorResouce).into(imageView);
	}

	/************************** url圆形图片加载 ****************************/
	public void loadCircleImage(String url, ImageView imageView) {
		displayCircleImage(url, imageView, mErrorResouce);
	}

	public void loadCircleImage(String url, ImageView imageView,
			int errorResouce) {
		displayCircleImage(url, imageView, errorResouce);
	}

	private void displayCircleImage(String url, ImageView imageView,
			int errorResouce) {
		if (GeneralUtil.isNumeric(url)) {
			// 如果全是数字认为它是资源
			Glide.with(imageView.getContext())
					.load(Integer.parseInt(url))
					.transform(new GlideCircleTransform(imageView.getContext()))
					.error(errorResouce).into(imageView);
		} else if (GeneralUtil.checkUrl(url)) {
			// 如果是一个合法的网络路径认为它是这个
			Glide.with(imageView.getContext())
					.load(url)
					.transform(new GlideCircleTransform(imageView.getContext()))
					.error(errorResouce).into(imageView);
		} else {
			// 认为他是一个本地文件
			File file = new File(url);
			if (file.exists()) {
				Glide.with(imageView.getContext())
						.load(file)
						.transform(
								new GlideCircleTransform(imageView.getContext()))
						.error(errorResouce).into(imageView);
			}
		}
	}

	/************************** 本地文件图片加载 ****************************/
	public void loadImage(File file, ImageView imageView) {
		Glide.with(imageView.getContext()).load(file).error(mErrorResouce)
				.into(imageView);
	}

	public void loadImage(File file, ImageView imageView, int errorResouce) {
		Glide.with(imageView.getContext()).load(file).error(errorResouce)
				.into(imageView);
	}

	/************************** 本地资源图片加载 ****************************/
	public void loadImage(int resouceId, ImageView imageView) {
		Glide.with(imageView.getContext()).load(resouceId).error(mErrorResouce)
				.into(imageView);
	}

	public void loadImage(int resouceId, ImageView imageView, int errorResouce) {
		Glide.with(imageView.getContext()).load(resouceId).error(errorResouce)
				.into(imageView);
	}

	/************************** url图片加载 ****************************/
	public void loadImage(String url, ImageView imageView) {
		display(url, imageView, mErrorResouce);
	}

	public void loadImage(String url, ImageView imageView, int errorResouce) {
		display(url, imageView, errorResouce);
	}

	private void display(String url, ImageView imageView, int errorResouce) {
		
		
		if (GeneralUtil.isNumeric(url)) {
			// 如果全是数字认为它是资源
			Glide.with(imageView.getContext()).load(Integer.parseInt(url))
					.error(errorResouce).centerCrop().into(imageView);
		} else if (GeneralUtil.checkUrl(url)) {
			// 如果是一个合法的网络路径认为它是这个
			Glide.with(imageView.getContext()).load(url).centerCrop().error(errorResouce)
					.into(imageView);
		} else {
			// 认为他是一个本地文件
			File file = new File(url);
			if (file.exists()) {
				Glide.with(imageView.getContext()).load(file)
						.error(errorResouce).centerCrop().into(imageView);
			}
		}
	}
}
