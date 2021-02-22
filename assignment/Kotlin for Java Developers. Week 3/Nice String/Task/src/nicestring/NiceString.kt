package nicestring



fun String.isNice(): Boolean {
    val pSubstring = this.contains("(ba|be|bu)".toRegex())
    val vowels = "aeiou"
    val atLeast3Vowels = this.run {
        var flag = false
        var counter = 0
        for (c in this) {
            if (vowels.contains(c)) {
                counter+=1
                if (counter == 3) {
                    flag = true
                    break
                }
            }
        }
        flag
    }

    var consecutive = false
    for(i in 1 until  this.length) {
        if (this[i] == this[i-1]) {
            consecutive = true
            break
        }
    }

    var c = 0
    if (!pSubstring)
        c+=1
    if (atLeast3Vowels)
        c+=1
    if (consecutive)
        c+=1

    return c > 1
}