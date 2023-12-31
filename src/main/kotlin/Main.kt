import kotlin.system.exitProcess

fun main() {
    var countCustoms = 0
    var buy = true
    val pizzaRestaurantPeter = PizzaRestaurantPeter(
        175.5,241.5,
        167.9,255.89
    )
    val pizzaRestaurantMoscow = PizzaRestaurantMoscow(
        400.8,375.67,
        846.23, 345.56
    )
    val pizzaRestaurantPskov = PizzaRestaurantPskov(
        120.50, 110.70,
        100.90, 135.70
    )

    val currentPizzaCity: PizzaCity

    println("Добрый день.В каком городе вы находитесь?")
    println("Санкт-Петербург - 1\nМосква - 2\nПсков - 3\nОтменить заказ - 4")
    when(readln()){
        "1" -> currentPizzaCity = pizzaRestaurantPeter
        "2" -> currentPizzaCity = pizzaRestaurantMoscow
        "3" -> currentPizzaCity = pizzaRestaurantPskov
        "4" ->{
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