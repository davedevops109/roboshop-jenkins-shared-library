def new1() {
    print "hello"
    def xyz = "hello"
    print "xyz = ${xyz}"
    print "abc = ${abc}"

    if (abc == "data") {
    print "yes"
    }
    else {
    print "no"
    }

    def x = 2
    def y = 0
    while(x > y) {
        println "${y}"
        y++
    }

    for(int i=0;i<5;i++) {
        println(i);
    }
}