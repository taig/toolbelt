package io.taig.android.content.operation

import android.app._
import android.app.job.JobScheduler
import android.app.usage.NetworkStatsManager
import android.content.Context._
import android.hardware.SensorManager
import android.location.LocationManager
import android.media.{ AudioManager, MediaRouter }
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.storage.StorageManager
import android.os.{ BatteryManager, PowerManager, Vibrator }
import android.telephony.{ CarrierConfigManager, SubscriptionManager, TelephonyManager }
import android.view.inputmethod.InputMethodManager
import android.view.{ LayoutInflater, WindowManager }
import io.taig.android.content.operation.Service.ServiceResolver

abstract class Service( context: android.content.Context ) {
    def service[T: ServiceResolver]: T = implicitly[ServiceResolver[T]].resolve( context )
}

object Service {
    abstract class ServiceResolver[T] {
        def resolve( context: android.content.Context ): T
    }

    implicit val `ServiceResolver[ActivityManager]` = new ServiceResolver[ActivityManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( ACTIVITY_SERVICE ).asInstanceOf[ActivityManager]
        }
    }

    implicit val `ServiceResolver[AlarmManager]` = new ServiceResolver[AlarmManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( ALARM_SERVICE ).asInstanceOf[AlarmManager]
        }
    }

    implicit val `ServiceResolver[AudioManager]` = new ServiceResolver[AudioManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( AUDIO_SERVICE ).asInstanceOf[AudioManager]
        }
    }

    implicit val `ServiceResolver[BatteryManager]` = new ServiceResolver[BatteryManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( BATTERY_SERVICE ).asInstanceOf[BatteryManager]
        }
    }

    implicit val `ServiceResolver[CarrierConfigManager]` = new ServiceResolver[CarrierConfigManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( CARRIER_CONFIG_SERVICE ).asInstanceOf[CarrierConfigManager]
        }
    }

    implicit val `ServiceResolver[ConnectivityManager]` = new ServiceResolver[ConnectivityManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( CONNECTIVITY_SERVICE ).asInstanceOf[ConnectivityManager]
        }
    }

    implicit val `ServiceResolver[DownloadManager]` = new ServiceResolver[DownloadManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( DOWNLOAD_SERVICE ).asInstanceOf[DownloadManager]
        }
    }

    implicit val `ServiceResolver[InputMethodManager]` = new ServiceResolver[InputMethodManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( INPUT_METHOD_SERVICE ).asInstanceOf[InputMethodManager]
        }
    }

    implicit val `ServiceResolver[JobScheduler]` = new ServiceResolver[JobScheduler] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( JOB_SCHEDULER_SERVICE ).asInstanceOf[JobScheduler]
        }
    }

    implicit val `ServiceResolver[KeyguardManager]` = new ServiceResolver[KeyguardManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( KEYGUARD_SERVICE ).asInstanceOf[KeyguardManager]
        }
    }

    implicit val `ServiceResolver[LayoutInflater]` = new ServiceResolver[LayoutInflater] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( LAYOUT_INFLATER_SERVICE ).asInstanceOf[LayoutInflater]
        }
    }

    implicit val `ServiceResolver[LocationManager]` = new ServiceResolver[LocationManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( LOCATION_SERVICE ).asInstanceOf[LocationManager]
        }
    }

    implicit val `ServiceResolver[MediaRouter]` = new ServiceResolver[MediaRouter] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( MEDIA_ROUTER_SERVICE ).asInstanceOf[MediaRouter]
        }
    }

    implicit val `ServiceResolver[NetworkStatsManager]` = new ServiceResolver[NetworkStatsManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( NETWORK_STATS_SERVICE ).asInstanceOf[NetworkStatsManager]
        }
    }

    implicit val `ServiceResolver[NotificationManager]` = new ServiceResolver[NotificationManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( NOTIFICATION_SERVICE ).asInstanceOf[NotificationManager]
        }
    }

    implicit val `ServiceResolver[PowerManager]` = new ServiceResolver[PowerManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( POWER_SERVICE ).asInstanceOf[PowerManager]
        }
    }

    implicit val `ServiceResolver[SearchManager]` = new ServiceResolver[SearchManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( SEARCH_SERVICE ).asInstanceOf[SearchManager]
        }
    }

    implicit val `ServiceResolver[SensorManager]` = new ServiceResolver[SensorManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( SENSOR_SERVICE ).asInstanceOf[SensorManager]
        }
    }

    implicit val `ServiceResolver[StorageManager]` = new ServiceResolver[StorageManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( STORAGE_SERVICE ).asInstanceOf[StorageManager]
        }
    }

    implicit val `ServiceResolver[SubscriptionManager]` = new ServiceResolver[SubscriptionManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( TELEPHONY_SUBSCRIPTION_SERVICE ).asInstanceOf[SubscriptionManager]
        }
    }

    implicit val `ServiceResolver[TelephonyManager]` = new ServiceResolver[TelephonyManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( TELEPHONY_SERVICE ).asInstanceOf[TelephonyManager]
        }
    }

    implicit val `ServiceResolver[UiModeManager]` = new ServiceResolver[UiModeManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( UI_MODE_SERVICE ).asInstanceOf[UiModeManager]
        }
    }

    implicit val `ServiceResolver[Vibrator]` = new ServiceResolver[Vibrator] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( VIBRATOR_SERVICE ).asInstanceOf[Vibrator]
        }
    }

    implicit val `ServiceResolver[WifiManager]` = new ServiceResolver[WifiManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( WIFI_SERVICE ).asInstanceOf[WifiManager]
        }
    }

    implicit val `ServiceResolver[WindowManager]` = new ServiceResolver[WindowManager] {
        override def resolve( context: android.content.Context ) = {
            context.getSystemService( WINDOW_SERVICE ).asInstanceOf[WindowManager]
        }
    }
}