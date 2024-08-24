package ZLapp.LockTask;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import java.lang.reflect.Method;

public class QsLock extends TileService {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onClick() {
		super.onClick();

		Intent intent = getPackageManager().getLaunchIntentForPackage("ZLapp.LockTask");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
		ActivityOptions activityOptions = ActivityOptions.makeBasic();
		Bundle bundle = activityOptions.toBundle();
		bundle.putInt("android.activity.windowingMode",5);
		bundle.putBoolean("Me",true);
		startActivity(intent, bundle);
		try {
			// 获取状态栏服务
			Object service = getSystemService("statusbar");
			if (service != null) {
				// 获取状态栏服务中的隐藏方法
				Class<?> statusBarManager = Class.forName("com.android.internal.statusbar.IStatusBarService$Stub");
				Method method = statusBarManager.getDeclaredMethod("asInterface", IBinder.class);
				Object statusBarService = method.invoke(statusBarManager, service);

				// 调用隐藏状态栏的方法
				Method hideMethod = statusBarService.getClass().getDeclaredMethod("hide", int.class);
				hideMethod.setAccessible(true);
				hideMethod.invoke(statusBarService, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Tile t=this.getQsTile();
		t.setState(Tile.STATE_INACTIVE);
		t.updateTile();
	}

	@Override
	public void onTileAdded() {
		super.onTileAdded();
		Tile t=this.getQsTile();
		t.setState(Tile.STATE_ACTIVE);
		t.updateTile();
	}

	@Override
	public void onTileRemoved() {
		Tile t=this.getQsTile();
		t.setState(Tile.STATE_ACTIVE);
		t.updateTile();
		super.onTileRemoved();
	}


}
