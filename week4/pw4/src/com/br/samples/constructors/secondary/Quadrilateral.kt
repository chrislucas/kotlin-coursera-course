package com.br.samples.constructors.secondary


// primary
class Quadrilateral(val h: Int, val w: Int) {
    //secondary
    constructor(side: Int) : this(side, side)
}