package controller;

import model.Map;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathFindingController {

    private static boolean isNotVisited(int x, List<Integer> path) {
        for (Integer integer : path)
            if (integer == x)
                return false;
        return true;
    }

    public static int findRemainingPathLength(int totalNumber) {
        int x = totalNumber / size;
        int y = totalNumber % size;
        return Math.abs(goalX - x) + Math.abs(goalY - y);
    }

    public static int findBestNode(List<Integer> lastNode, List<Integer> path) {
        int minimumLength = 9999999;
        for (Integer value : lastNode) {
            if (isNotVisited(value, path)) {
                if (findRemainingPathLength(value) < minimumLength) {
                    minimumLength = findRemainingPathLength(value);
                }
            }
        }
        for (Integer integer : lastNode) {
            if (isNotVisited(integer, path)) {
                if (findRemainingPathLength(integer) == minimumLength) {
                    return integer;
                }
            }
        }
        return 0;
    }

    private static List<Integer> findPaths(List<List<Integer>> g, int src, int dst) {
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(src);
        queue.offer(path);

        while (!queue.isEmpty()) {
            path = queue.poll();
            int last = path.get(path.size() - 1);
            if (last == dst) {
                return path;
            }
            List<Integer> lastNode = g.get(last);
            List<Integer> newPath = new ArrayList<>(path);
            if (findBestNode(lastNode, path) != 0) {
                newPath.add(findBestNode(lastNode, path));
                queue.offer(newPath);
            } else {
                return path;
            }
        }
        return null;
    }

    public static void constructGraph(List<List<Integer>> g, boolean[][] map) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j])
                    continue;
                if (i != size - 1) {
                    if (!map[i + 1][j]) {
                        g.get(size * i + j).add(size * (i + 1) + j);
                    }
                }
                if (i != 0) {
                    if (!map[i - 1][j]) {
                        g.get(size * i + j).add(size * (i - 1) + j);
                    }
                }
                if (j != size - 1) {
                    if (!map[i][j + 1]) {
                        g.get(size * i + j).add(size * i + j + 1);
                    }
                }
                if (j != 0) {
                    if (!map[i][j - 1]) {
                        g.get(size * i + j).add(size * i + j - 1);
                    }
                }
            }
        }
    }

    public static void graphForWalls(List<List<Integer>> g, boolean[][] map) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j])
                    continue;
                if (i != size - 1) {
                    if (map[i + 1][j]) {
                        g.get(size * i + j).add(size * (i + 1) + j);
                    }
                }
                if (i != 0) {
                    if (map[i - 1][j]) {
                        g.get(size * i + j).add(size * (i - 1) + j);
                    }
                }
                if (j != size - 1) {
                    if (map[i][j + 1]) {
                        g.get(size * i + j).add(size * i + j + 1);
                    }
                }
                if (j != 0) {
                    if (map[i][j - 1]) {
                        g.get(size * i + j).add(size * i + j - 1);
                    }
                }
            }
        }
    }

    //TODO : take care that when you want to pass the x and y make sure to -1 them
    public static int size = Map.mapSize;
    public static int goalX;
    public static int goalY;
    public static int startX;
    public static int startY;
    public static boolean[][] notPassable;
    public static boolean[][] wall;

    public static List<Integer> pathFinding() {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i <= size * size; i++) {
            g.add(new ArrayList<>());
        }
        constructGraph(g, notPassable);
        graphForWalls(g, wall);
        if (!notPassable[goalX][goalY]) {
            int src = startX * size + startY, dst = goalX * size + goalY;
            return findPaths(g, src, dst);
        }
        return null;
    }
}
