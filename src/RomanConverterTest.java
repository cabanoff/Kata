
import static org.junit.Assert.*;
import org.junit.Test;


public class RomanConverterTest {

    @Test
    public void testArabicToRoman_ValidNumbers() {
        assertEquals("I", Main.RomanConverter.arabicToRoman(1));
        assertEquals("IV", Main.RomanConverter.arabicToRoman(4));
        assertEquals("X", Main.RomanConverter.arabicToRoman(10));
        assertEquals("XLIX", Main.RomanConverter.arabicToRoman(49));
        assertEquals("C", Main.RomanConverter.arabicToRoman(100));
        assertEquals("MMMCMXCVIII", Main.RomanConverter.arabicToRoman(3998));
    }

    @Test
    public void testArabicToRoman_BoundaryNumbers() {
        assertEquals("MMMCMXCIX", Main.RomanConverter.arabicToRoman(3999));
        assertEquals("I",Main.RomanConverter.arabicToRoman(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArabicToRoman_NumberTooLow() {
        Main.RomanConverter.arabicToRoman(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArabicToRoman_NumberTooHigh() {
        Main.RomanConverter.arabicToRoman(4000);
    }

    @Test
    public void testRomanToArabic_ValidNumbers() {
        assertEquals(1, Main.RomanConverter.romanToArabic("I"));
        assertEquals(4, Main.RomanConverter.romanToArabic("IV"));
        assertEquals(7, Main.RomanConverter.romanToArabic("VII"));
        assertEquals(9, Main.RomanConverter.romanToArabic("IX"));
        assertEquals(40, Main.RomanConverter.romanToArabic("XL"));
        assertEquals(100, Main.RomanConverter.romanToArabic("C"));
    }

    @Test
    public void testRomanToArabic_BoundaryNumbers() {
        assertEquals(3999, Main.RomanConverter.romanToArabic("MMMCMXCIX"));
        assertEquals(1, Main.RomanConverter.romanToArabic("I"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRomanToArabic_InvalidRomanNumber() {
        Main.RomanConverter.romanToArabic("IIII");
    }

}
