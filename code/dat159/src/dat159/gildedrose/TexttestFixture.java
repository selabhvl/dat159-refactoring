package dat159.gildedrose;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

    private Path workingDir;

    @Before
    public void init() {
        this.workingDir = Path.of("", "src/dat159/gildedrose");
    }

    @Test
    public void read() throws IOException {
    	// Capture stdout:
    	final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(myOut));

    	// If Eclipse doesn't find the expected output,
    	// this statement can help in tracking it down:
    	System.err.println(this.workingDir.toUri().toString());
    	
        Path file = this.workingDir.resolve("expected.out");
        String content = Files.readString(file);
        
        // Run "main":
        TexttestFixture.main(new String[] {"30"});
        
        // Check:
        assertThat(myOut.toString(),is(content));
    }
}