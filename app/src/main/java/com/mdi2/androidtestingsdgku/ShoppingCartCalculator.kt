package com.mdi2.androidtestingsdgku

/**
 * 1. Create a Shopping Cart
 *  * if the shopping cart is empty return 0
 * 2. I want to be able to add items to my shopping cart
 * 3. I want to be able to remove items from my shopping cart
 * 4. I should be able to calculate the subtotal of my shopping cart
 *    calculate the subtotal of all items
 *    apply a discount of 10% if subtotal is Greater than 200 and 20% if is Greater than 300
 */

class ShoppingCartCalculator {
    private val items: MutableList<Item> = mutableListOf()
    fun subtotal(): Double {
        var subtotal = 0.0
        for(item in items){
            subtotal += item.price
        }
        if (subtotal > 300){
            subtotal *= 0.8
        } else if (subtotal > 200){
            subtotal *= 0.9
        }
        return subtotal
    }
    fun addItem(item: Item) {
        // implement to add item to the cart
        items.add(item)
    }

    fun removeItem(item: Item){
        items.remove(item)
    }
}

data class Item(val name: String, val price: Double)