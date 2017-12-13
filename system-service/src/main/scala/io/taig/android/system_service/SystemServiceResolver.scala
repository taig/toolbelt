package io.taig.android.system_service

import android.app._
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.content.Context._
import android.hardware.SensorManager
import android.location.LocationManager
import android.media.{AudioManager, MediaRouter}
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.storage.StorageManager
import android.os.{BatteryManager, PowerManager, Vibrator}
import android.telephony.{
  CarrierConfigManager,
  SubscriptionManager,
  TelephonyManager
}
import android.view.inputmethod.InputMethodManager
import android.view.{LayoutInflater, WindowManager}

trait SystemServiceResolver[T] {
  def resolve(context: Context): T
}

object SystemServiceResolver {
  def apply[T: SystemServiceResolver]: SystemServiceResolver[T] =
    implicitly[SystemServiceResolver[T]]

  def instance[T](f: Context ⇒ T): SystemServiceResolver[T] =
    new SystemServiceResolver[T] {
      override def resolve(context: Context) = f(context)
    }

  private def load[T](name: String)(implicit c: Context): T = {
    c.getSystemService(name).asInstanceOf[T]
  }

  implicit val systemServiceResolver_ActivityManager
    : SystemServiceResolver[ActivityManager] = {
    instance { implicit context ⇒
      load[ActivityManager](ACTIVITY_SERVICE)
    }
  }

  implicit val systemServiceResolver_AlarmManager
    : SystemServiceResolver[AlarmManager] = {
    instance { implicit context ⇒
      load[AlarmManager](ALARM_SERVICE)
    }
  }

  implicit val systemServiceResolver_AudioManager
    : SystemServiceResolver[AudioManager] = {
    instance { implicit context ⇒
      load[AudioManager](AUDIO_SERVICE)
    }
  }

  implicit val systemServiceResolver_BatteryManager
    : SystemServiceResolver[BatteryManager] = {
    instance { implicit context ⇒
      load[BatteryManager](BATTERY_SERVICE)
    }
  }

  implicit val systemServiceResolver_CarrierConfigManager
    : SystemServiceResolver[CarrierConfigManager] = {
    instance { implicit context ⇒
      load[CarrierConfigManager](CARRIER_CONFIG_SERVICE)
    }
  }

  implicit val systemServiceResolver_ConnectivityManager
    : SystemServiceResolver[ConnectivityManager] = {
    instance { implicit context ⇒
      load[ConnectivityManager](CONNECTIVITY_SERVICE)
    }
  }

  implicit val systemServiceResolver_DownloadManager
    : SystemServiceResolver[DownloadManager] = {
    instance { implicit context ⇒
      load[DownloadManager](DOWNLOAD_SERVICE)
    }
  }

  implicit val systemServiceResolver_InputMethodManager
    : SystemServiceResolver[InputMethodManager] = {
    instance { implicit context ⇒
      load[InputMethodManager](INPUT_METHOD_SERVICE)
    }
  }

  implicit val systemServiceResolver_JobScheduler
    : SystemServiceResolver[JobScheduler] = {
    instance { implicit context ⇒
      load[JobScheduler](JOB_SCHEDULER_SERVICE)
    }
  }

  implicit val systemServiceResolver_KeyguardManager
    : SystemServiceResolver[KeyguardManager] = {
    instance { implicit context ⇒
      load[KeyguardManager](KEYGUARD_SERVICE)
    }
  }

  implicit val systemServiceResolver_LayoutInflater
    : SystemServiceResolver[LayoutInflater] = {
    instance { implicit context ⇒
      load[LayoutInflater](LAYOUT_INFLATER_SERVICE)
    }
  }

  implicit val systemServiceResolver_LocationManager
    : SystemServiceResolver[LocationManager] = {
    instance { implicit context ⇒
      load[LocationManager](LOCATION_SERVICE)
    }
  }

  implicit val systemServiceResolver_MediaRouter
    : SystemServiceResolver[MediaRouter] = {
    instance { implicit context ⇒
      load[MediaRouter](MEDIA_ROUTER_SERVICE)
    }
  }

  implicit val systemServiceResolver_NetworkStatsManager
    : SystemServiceResolver[NetworkStatsManager] = {
    instance { implicit context ⇒
      load[NetworkStatsManager](NETWORK_STATS_SERVICE)
    }
  }

  implicit val systemServiceResolver_NotificationManager
    : SystemServiceResolver[NotificationManager] = {
    instance { implicit context ⇒
      load[NotificationManager](NOTIFICATION_SERVICE)
    }
  }

  implicit val systemServiceResolver_PowerManager
    : SystemServiceResolver[PowerManager] = {
    instance { implicit context ⇒
      load[PowerManager](POWER_SERVICE)
    }
  }

  implicit val systemServiceResolver_SearchManager
    : SystemServiceResolver[SearchManager] = {
    instance { implicit context ⇒
      load[SearchManager](SEARCH_SERVICE)
    }
  }

  implicit val systemServiceResolver_SensorManager
    : SystemServiceResolver[SensorManager] = {
    instance { implicit context ⇒
      load[SensorManager](SENSOR_SERVICE)
    }
  }

  implicit val systemServiceResolver_StorageManager
    : SystemServiceResolver[StorageManager] = {
    instance { implicit context ⇒
      load[StorageManager](STORAGE_SERVICE)
    }
  }

  implicit val systemServiceResolver_SubscriptionManager
    : SystemServiceResolver[SubscriptionManager] = {
    instance { implicit context ⇒
      load[SubscriptionManager](TELEPHONY_SUBSCRIPTION_SERVICE)
    }
  }

  implicit val systemServiceResolver_TelephonyManager
    : SystemServiceResolver[TelephonyManager] = {
    instance { implicit context ⇒
      load[TelephonyManager](TELEPHONY_SERVICE)
    }
  }

  implicit val systemServiceResolver_UiModeManager
    : SystemServiceResolver[UiModeManager] = {
    instance { implicit context ⇒
      load[UiModeManager](UI_MODE_SERVICE)
    }
  }

  implicit val systemServiceResolver_Vibrator
    : SystemServiceResolver[Vibrator] = {
    instance { implicit context ⇒
      load[Vibrator](VIBRATOR_SERVICE)
    }
  }

  implicit val systemServiceResolver_WifiManager
    : SystemServiceResolver[WifiManager] = {
    instance { implicit context ⇒
      load[WifiManager](WIFI_SERVICE)
    }
  }

  implicit val systemServiceResolver_WindowManager
    : SystemServiceResolver[WindowManager] = {
    instance { implicit context ⇒
      load[WindowManager](WINDOW_SERVICE)
    }
  }
}
