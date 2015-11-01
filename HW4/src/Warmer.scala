trait HeaterComponent {
  val heater: HeaterService

  trait HeaterService {
    def startHeating(): Unit

    def stopHeating(): Unit
  }

}

trait WaterSensorComponent {
  val waterSensor: WaterSensorDevice

  trait WaterSensorDevice {
    def getWaterLevel: Int
  }

}

trait WaterIndicatorComponent {
  this: WaterSensorComponent =>
  val waterIndicator: WaterIndicator

  trait WaterIndicator {
    val min: Int
    val max: Int

    def hasWater: Boolean = {
      val level: Int = waterSensor.getWaterLevel
      level > min && level < max
    }
  }

}

trait TemperatureSensorComponent {
  val temperatureSensor: TemperatureSensorDevice

  trait TemperatureSensorDevice {
    def getTemperature: Int
  }

}

trait WarmerComponent {

  val warmer: Warmer

  trait Warmer {
    def push(): Unit
  }

}

trait SimpleWarmerComponent extends WarmerComponent {
  this: HeaterComponent with WaterSensorComponent with WaterIndicatorComponent =>
  override val warmer = new Warmer {
    override def push(): Unit = {
      if (waterIndicator.hasWater) heater.startHeating()
      else heater.stopHeating()
    }
  }
}

trait WarmerWithTemperatureCheckingComponent extends WarmerComponent {
  this: HeaterComponent with WaterSensorComponent with WaterIndicatorComponent with TemperatureSensorComponent =>
  override val warmer = new Warmer {
    override def push(): Unit = {
      if (waterIndicator.hasWater && temperatureSensor.getTemperature < 98) heater.startHeating()
      else heater.stopHeating()
    }
  }
}

object SimpleWarmerComponentRegistry
  extends HeaterComponent with WaterSensorComponent with WaterIndicatorComponent with SimpleWarmerComponent {

  override val heater: HeaterService = new HeaterService {
    override def startHeating(): Unit = {
      println("Starts heating")
    }

    override def stopHeating(): Unit = {
      println("Stop heating")
    }
  }
  override val waterSensor: WaterSensorDevice = new WaterSensorDevice {
    override def getWaterLevel: Int = 500
  }
  override val waterIndicator: WaterIndicator = new WaterIndicator {
    override val max: Int = 1000
    override val min: Int = 100
  }
}

object WarmerWithTemperatureCheckingComponentRegistry extends HeaterComponent with WaterSensorComponent with WaterIndicatorComponent with TemperatureSensorComponent with WarmerWithTemperatureCheckingComponent {
  override val heater: HeaterService = new HeaterService {
    override def startHeating(): Unit = {
      println("Starts heating")
    }

    override def stopHeating(): Unit = {
      println("Stop heating")
    }
  }
  override val waterSensor: WaterSensorDevice = new WaterSensorDevice {
    override def getWaterLevel: Int = 0
  }
  override val waterIndicator: WaterIndicator = new WaterIndicator {
    override val max: Int = 500
    override val min: Int = 50
  }
  override val temperatureSensor: TemperatureSensorDevice = new TemperatureSensorDevice {
    override def getTemperature: Int = 50
  }
}


object WarmerTestObject {
  val firstWarmer = SimpleWarmerComponentRegistry.warmer
  val secondWarmer = WarmerWithTemperatureCheckingComponentRegistry.warmer

  def main(args: Array[String]) {
    firstWarmer.push()
    secondWarmer.push()
  }
}
