import kotlin.system.exitProcess

fun main() {
    var countCustoms = 0
    var buy = true
    val pizzaRestaurantPeter = PizzaRestaurantPeter(
        175.5,241.5,
        167.9,255.89
    )
    val pizzaRestaurantMoscow = PizzaRestaurantMoscow(
        600.8,981.67,
        546.23, 1234.56
    )
    val currentPizzaCity: PizzaCity

    println("Добрый день.В каком городе вы находитесь?")
    println("Санкт-Петербург - 1\nМосква - 2\nОтменить заказ - 3")
    when(readln()){
        "1" -> currentPizzaCity = pizzaRestaurantPeter
        "2" -> currentPizzaCity = pizzaRestaurantMoscow
        "3" ->{
            currentPizzaCity = pizzaRestaurantMoscow
            buy = false
        }
        else -> {
            println("Вы ввели не то значение")
            exitProcess(1)
        }
    }
    while (buy){
        countCustoms++
        println("\nВыбирите пиццу:")
        println("1.Неопалитанская пицца\n2.Римская пицца\n3.Сицилийская пицца\n4.Тирольская пицца" +
                "\n5.Показать статистику")
        when (readln()) {
            "1" -> currentPizzaCity.neapolitanPizzaSale()
            "2" -> currentPizzaCity.romanPizzaSale()
            "3" -> currentPizzaCity.sicilianPizzaSale()
            "4" -> currentPizzaCity.tyroleanPizzaSale()
            "5" -> currentPizzaCity.showStatistic(countCustoms)
            else -> {
                println("Вы ввели не то значение")
                exitProcess(1)
            }
        }
        println("Если хотите закончить день - exit")
        if (readln() == "exit") buy = false
        println()

    }
}