static HI: &str = "hi";

fn create() -> String {
    let s = String::from(HI);
    return s; //所有权转移，从函数内部移动到外部
}

fn consume(s: String) { //所有权转移，从函数外部移动到内部
    println!("{}", s);
}

pub fn execute() {
    let s = create();
    consume(s);
}

/* 示例来自 深入浅出Rust https://book.douban.com/subject/30312231/ 116页 */