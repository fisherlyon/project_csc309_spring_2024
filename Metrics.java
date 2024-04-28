import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Metrics {

    public static LineCounts countLines(String fileName) throws IOException {
        int totalLines = 0;
        int effectiveLines = 0;
        int logicalLines = 0;
        int forCount = 0;
        int whileCount = 0;
        int ifCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean inMultiLineComment = false;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                // Ignore empty lines
                if (line.isEmpty()) {
                    continue;
                }

                totalLines++;

                // Ignore single line comments
                if (line.startsWith("//")) {
                    continue;
                }
                // Handle multi-line comments
                if (inMultiLineComment) {
                    if (line.contains("*/")) {
                        inMultiLineComment = false;
                    }
                    continue;
                } else if (line.startsWith("/*")) {
                    inMultiLineComment = true;
                    continue;
                }
                // Check if line contains a standalone opening or closing brace
                if (line.equals("{") || line.equals("}")) {
                    continue;
                }
                effectiveLines++;
                // Check if line contains a semicolon
                if (line.contains(";")) {
                    logicalLines++;
                }
            }
        }
        return new LineCounts(totalLines, effectiveLines, logicalLines, forCount, whileCount, ifCount);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java LineCounter <file_name>");
            System.exit(1);
        }

        String fileName = args[0];
        try {
            LineCounts lineCounts = countLines(fileName);
            System.out.println("LOC:    " + lineCounts.getTotalLines());
            System.out.println("eLOC    " + lineCounts.getEffectiveLines());
            System.out.println("lLOC    " + lineCounts.getLogicalLines());
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}

class LineCounts {
    private final int totalLines;
    private final int effectiveLines;
    private final int logicalLines;
    private final int forCount;
    private final int whileCount;
    private final int ifCount;

    public LineCounts(int totalLines, int effectiveLines, int logicalLines, int forCount, int whileCount, int ifCount) {
        this.totalLines = totalLines;
        this.effectiveLines = effectiveLines;
        this.logicalLines = logicalLines;
        this.forCount = forCount;
        this.whileCount = whileCount;
        this.ifCount = ifCount;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public int getEffectiveLines() {
        return effectiveLines;
    }

    public int getLogicalLines() {
        return logicalLines;
    }

    public int getForCount() {
        return forCount;
    }

    public int getWhileCount() {
        return whileCount;
    }

    public int getIfCount() {
        return ifCount;
    }
}
