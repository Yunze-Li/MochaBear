package com.arctos.mochabear.blog

class KotlinScopeFunctions {

    fun useApply() {
        val person = Person(name = "arctos", age = 29)

        // 1 year later...
        person.apply {
            age = 30	// 等同于this.age = 30
        }
        println(person) // 这里age = 30
    }

    fun useAlso() {
        val person = Person(name = "arctos", age = 29)

        // 1 year later...
        person.also {
            it.age = 30
            playWith(it)
        }
        println(person) // 这里age = 30
    }

    fun useRun() {
        val person = Person(name = "arctos", age = 29)

        // 1 year later...
        val updatedAge = person.run {
            age = 30	// 等同于this.age = 30
            age * 10  // 返回一个计算的结果
        }
        println(updatedAge) // 这里updatedAge = 300
    }

    fun useRun2() {
        val person = Person(name = "arctos", age = 29)

        // 1 year later...
        run {
            person.age = 30
            println(person.age)
        }
    }

    fun useLet() {
        val someone: Person? = null

        someone?.let {
            it.name = "arctos"  // 仅当someone不为空时执行代码块
        }

        val person = Person(name = "arctos", age = 29)

        // 1 year later and I'm moving...
        someone?.let { arctos ->
            arctos.age = 30
            arctos.address = "new address"
        }
    }

    fun useWith() {
        val person = Person(name = "arctos", age = 29)

        // 1 year later...
        val personInfo = with(person) {
            age = 30
            PersonInfo(name, age, address) // lambda表达式结果为创建的PersonInfo对象
        }
        println(personInfo.age) // 这里age = 30
    }

    fun playWith(person: Person) {
        // let's play!!!
    }

}

class Person(
    var name: String,
    var age: Int,
    var address: String? = null
)

class PersonInfo(
    var name: String,
    var age: Int,
    var address: String?,
)