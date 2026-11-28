package com.boswelja.effectivefortnight

val TRIPLETS = setOf(
    // W..
    Triplet("wa", 'わ', 'ワ'),
    Triplet("wo", 'を', 'ヲ'),
    Triplet("n", 'ん', 'ン'),
    // R..
    Triplet("ra", 'ら', 'ㇻ'),
    Triplet("ri", 'り', 'ㇼ'),
    Triplet("ru", 'る', 'ㇽ'),
    Triplet("re", 'れ', 'レ'),
    Triplet("ro", 'ろ', 'ㇿ'),
    // Y..
    Triplet("ya", 'や', 'ャ'),
    Triplet("yu", 'ゆ', 'ユ'),
    Triplet("yo", 'よ', 'ョ'),
    // M..
    Triplet("ma", 'ま', 'マ'),
    Triplet("mi", 'み', '三'),
    Triplet("mu", 'む', 'ム'),
    Triplet("me", 'め', 'メ'),
    Triplet("mo", 'も', 'モ'),
    // H..
    Triplet("ha", 'は', 'ハ'),
    Triplet("hi", 'ひ', 'ㇶ'),
    Triplet("fu", 'ふ', 'ㇷ'),
    Triplet("he", 'へ', 'ㇸ'),
    Triplet("ho", 'ほ', 'ㇹ'),
    // N..
    Triplet("na", 'な', 'ナ'),
    Triplet("ni", 'に', 'ニ'),
    Triplet("nu", 'ぬ', 'ㇴ'),
    Triplet("ne", 'ね', 'ネ'),
    Triplet("no", 'の', 'ノ'),
    // T..
    Triplet("ta", 'た', 'タ'),
    Triplet("chi", 'ち', 'チ'),
    Triplet("tsu", 'つ', 'ツ'),
    Triplet("te", 'て', 'テ'),
    Triplet("to", 'と', 'ㇳ'),
    // S..
    Triplet("sa", 'さ', 'サ'),
    Triplet("shi", 'し', 'シ'),
    Triplet("su", 'す', 'ス'),
    Triplet("se", 'せ', 'セ'),
    Triplet("so", 'そ', 'ソ'),
    // K..
    Triplet("ka", 'か', 'カ'),
    Triplet("ki", 'き', 'キ'),
    Triplet("ku", 'く', 'ク'),
    Triplet("ke", 'け', 'ケ'),
    Triplet("ko", 'こ', 'コ'),
    // etc
    Triplet("a", 'あ', 'ア'),
    Triplet("i", 'い', 'イ'),
    Triplet("u", 'う', 'ウ'),
    Triplet("e", 'え', 'エ'),
    Triplet("o", 'お', 'オ'),
)

data class Triplet(
    val romaji: String,
    val hiragana: Char,
    val katakana: Char,
)
