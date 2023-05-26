package leetcode.q417_太平洋大西洋水流问题;



import java.util.*;

public interface Question {
    List<List<Integer>> pacificAtlantic(int[][] heights);
}

class Test {
    public static void main(String[] args) {

    }
}

/**
 * 题解目录
 * {@link Solution1} 动态规划 有可能通过未遍历的格子连通 {}
 * {@link Solution2} 递归四周找（防止反复 击败5-24
 * {@link Solution3} 改进s2 添加2个记录表 击败7-42 没啥提升
 * {@link Solution4} 尝试官方深度优先搜索 击败89-43
 * {@link Solution5} 尝试官方广度优先搜索 击败38-92
 */
class Solution5 implements Question {

    private static final int[][] offsets = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    @Override
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] p = new boolean[m][n];
        boolean[][] a = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            bfs(heights, i, 0, p);
            bfs(heights, m - i - 1, n - 1, a);
        }
        for (int i = 1; i < n; i++) {
            bfs(heights, 0, i, p);
            bfs(heights, m - 1, n - i - 1, a);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (p[i][j] && a[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] heights, int i, int j, boolean[][] ocean) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            i = poll[0];
            j = poll[1];
            if (ocean[i][j]) {
                continue;
            }
            ocean[i][j] = true;
            for (int[] pair : offsets) {
                int newRow = i + pair[0];
                int newLine = j + pair[1];
                if (newRow >= 0 && newRow < ocean.length && newLine >= 0 && newLine < ocean[0].length && heights[i][j] <= heights[newRow][newLine]) {
                    queue.offer(new int[]{newRow, newLine});
                }
            }
        }

    }
}

class Solution4 implements Question {
    private static final int[][] offsets = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    @Override
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] p = new boolean[m][n];
        boolean[][] a = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, p);
            dfs(heights, m - i - 1, n - 1, a);
        }
        for (int i = 1; i < n; i++) {
            dfs(heights, 0, i, p);
            dfs(heights, m - 1, n - i - 1, a);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (p[i][j] && a[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, int i, int j, boolean[][] ocean) {
        if (ocean[i][j]) {
            return;
        }
        ocean[i][j] = true;
        for (int[] pair : offsets) {
            int newRow = i + pair[0];
            int newLine = j + pair[1];
            if (newRow >= 0 && newRow < ocean.length && newLine >= 0 && newLine < ocean[0].length && heights[i][j] <= heights[newRow][newLine]) {
                dfs(heights, newRow, newLine, ocean);
            }
        }
    }
}

class Solution3 implements Question {

    @Override
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int rows = heights.length;
        int lines = heights[0].length;
        boolean p[][] = new boolean[rows][lines];
        boolean a[][] = new boolean[rows][lines];
        // 初始化状态转移数组
        for (int i = 0; i < rows; i++) {
            p[i][0] = true;
            a[i][lines - 1] = true;
        }
        for (int i = 0; i < lines; i++) {
            p[0][i] = true;
            a[rows - 1][i] = true;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < lines; j++) {
                if (canReach(heights, i, j, new boolean[rows][lines], p, a, true) && canReach(heights, i, j, new boolean[rows][lines], p, a, false)) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private boolean canReach(int[][] heights, int i, int j, boolean[][] passed, boolean[][] p, boolean[][] a, boolean isPacific) {
        if ((isPacific && (i == 0 || j == 0)) || !isPacific && (i == heights.length - 1 || j == heights[0].length - 1)) {
            return true;
        }
        int cur = heights[i][j];
        passed[i][j] = true;
        boolean res = (i > 0 && cur >= heights[i - 1][j] && !passed[i - 1][j] && ((isPacific ? p[i - 1][j] : a[i - 1][j]) || canReach(heights, i - 1, j, passed, p, a, isPacific)))
                || (j > 0 && cur >= heights[i][j - 1] && !passed[i][j - 1] && ((isPacific ? p[i][j - 1] : a[i][j - 1]) || canReach(heights, i, j - 1, passed, p, a, isPacific)))
                || (i < heights.length - 1 && cur >= heights[i + 1][j] && !passed[i + 1][j] && ((isPacific ? p[i + 1][j] : a[i + 1][j]) || canReach(heights, i + 1, j, passed, p, a, isPacific)))
                || (j < heights[0].length - 1 && cur >= heights[i][j + 1] && !passed[i][j + 1] && ((isPacific ? p[i][j + 1] : a[i][j + 1]) || canReach(heights, i, j + 1, passed, p, a, isPacific)));
        if (res) {
            if (isPacific) p[i][j] = true;
            if (!isPacific) a[i][j] = true;
        }
        return res;
    }
}

class Solution2 implements Question {
    @Override
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (canReach(heights, i, j, new boolean[heights.length][heights[0].length], true) && canReach(heights, i, j, new boolean[heights.length][heights[0].length], false)) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private boolean canReach(int[][] heights, int i, int j, boolean[][] passed, boolean isPacific) {
        if ((isPacific && (i == 0 || j == 0)) || !isPacific && (i == heights.length - 1 || j == heights[0].length - 1)) {
            return true;
        }
        int cur = heights[i][j];
        passed[i][j] = true;
        return (i > 0 && !passed[i - 1][j] && cur >= heights[i - 1][j] && canReach(heights, i - 1, j, passed, isPacific))
                || (j > 0 && !passed[i][j - 1] && cur >= heights[i][j - 1] && canReach(heights, i, j - 1, passed, isPacific))
                || (i < heights.length - 1 && !passed[i + 1][j] && cur >= heights[i + 1][j] && canReach(heights, i + 1, j, passed, isPacific))
                || (j < heights[0].length - 1 && !passed[i][j + 1] && cur >= heights[i][j + 1] && canReach(heights, i, j + 1, passed, isPacific));
    }
}

class Solution1 implements Question {

    /**
     * 能流到能到某一个大洋的格子，自己就能
     *
     * @param heights
     * @return
     */
    @Override
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int rows = heights.length;
        int lines = heights[0].length;
        boolean p[][] = new boolean[rows][lines];
        boolean a[][] = new boolean[rows][heights[0].length];
        // 初始化状态转移数组
        for (int i = 0; i < rows; i++) {
            p[i][0] = true;
            a[i][lines - 1] = true;
        }
        for (int i = 0; i < lines; i++) {
            p[0][i] = true;
            a[rows - 1][i] = true;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < lines; j++) {
                int cur = heights[i][j];
                if (!p[i][j]) {
                    p[i][j] = (cur >= heights[i - 1][j] && p[i - 1][j])
                            || (cur >= heights[i][j - 1] && p[i][j - 1]);
                }
                int ri = rows - i - 1;
                int rj = lines - j - 1;
                cur = heights[ri][rj];
                if (!a[ri][rj]) {
                    a[ri][rj] = (cur >= heights[ri + 1][rj] && a[ri + 1][rj])
                            || (cur >= heights[ri][rj + 1] && a[ri][rj + 1]);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < lines; j++) {
                if (p[i][j] && a[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }
}