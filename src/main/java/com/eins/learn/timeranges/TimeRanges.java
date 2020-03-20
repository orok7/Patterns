package com.eins.learn.timeranges;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

@Getter
public final class TimeRanges {

    private final List<TimeRange> parts;

    private TimeRanges(List<TimeRange> parts) {
        this.parts = parts;
    }

    public int size() {
        return parts.size();
    }

    public TimeRange get(int index) {
        return parts.get(index);
    }

    public Stream<TimeRange> stream() {
        return parts.stream();
    }

    public TimeRanges merge(TimeRanges others) {
        if (others == null) {
            return this;
        }
        List<TimeRange> union = ListUtils.union(this.parts, others.parts);
        union.sort(Comparator.comparing(TimeRange::getStart));
        List<TimeRange> result = new ArrayList<>();
        result.add(union.get(0));
        for (int i = 1; i < union.size(); i++) {
            TimeRange removed = result.remove(result.size() - 1);
            TimeRanges merged = removed.merge(union.get(i));
            result.addAll(merged.parts);
        }
        return new TimeRanges(result);
    }

    public TimeRanges exclude(TimeRanges others) {
        if (others == null) {
            return this;
        }
        Map<TimeRange, Boolean> map = new HashMap<>();
        this.parts.forEach(p -> map.put(TimeRange.of(p), true));
        others.parts.forEach(p -> map.put(TimeRange.of(p), false));
        List<TimeRange> union = new ArrayList<>(map.keySet());
        union.sort(Comparator.comparing(TimeRange::getStart));
        List<TimeRange> result = new ArrayList<>();
        for (int i = 0; i < union.size(); i++) {
            TimeRange current = union.get(i);
            if (map.get(current)) {
                result.add(current);
            } else {
                if (result.size() > 0) {
                    TimeRange removed = result.remove(result.size() - 1);
                    List<TimeRange> parts = removed.exclude(current).parts;
                    if (!parts.isEmpty()) {
                        result.addAll(parts);
                    } else {
                        union.add(i + 1, current);
                    }
                }
                if (i + 1 < union.size()) {
                    TimeRange next = union.get(i + 1);
                    if (map.get(next)) {
                        List<TimeRange> parts = next.exclude(current).parts;
                        if (!parts.isEmpty()) {
                            result.addAll(parts);
                            i++;
                        } else {
                            union.set(i + 1, current);
                        }
                    }
                }
            }
        }
        return new TimeRanges(result);
    }

    public static TimeRanges empty() {
        return new TimeRanges(new ArrayList<>());
    }

    public static TimeRanges of(LocalTime start, LocalTime end) {
        return of(TimeRange.of(start, end));
    }

    public static <T> TimeRanges ofTime(Collection<T> list, Function<T, LocalTime> getStart,
        Function<T, LocalTime> getEnd) {
        return CollectionUtils.emptyIfNull(list).stream()
            .map(range -> TimeRanges.of(getStart.apply(range), getEnd.apply(range)))
            .reduce(TimeRanges::merge).orElse(empty());
    }

    public static <T> TimeRanges ofDateTime(Collection<T> list, Function<T, LocalDateTime> getStart,
        Function<T, LocalDateTime> getEnd) {
        return CollectionUtils.emptyIfNull(list).stream()
            .map(range -> TimeRanges.of(getStart.apply(range).toLocalTime(), getEnd.apply(range).toLocalTime()))
            .reduce(TimeRanges::merge).orElse(empty());
    }

    private static TimeRanges of(TimeRange r1) {
        return new TimeRanges(new ArrayList<>(List.of(r1)));
    }

    private static TimeRanges of(TimeRange r1, TimeRange r2) {
        return new TimeRanges(new ArrayList<>(List.of(r1, r2)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeRanges that = (TimeRanges) o;
        return Objects.equals(parts, that.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parts);
    }

    @Override
    public String toString() {
        return "TimeRanges{" +
            "parts=" + parts +
            '}';
    }

    @Getter
    public static final class TimeRange {

        private final LocalTime start;
        private final LocalTime end;

        private TimeRange(LocalTime start, LocalTime end) {
            this.start = start;
            this.end = end;
            validate(this);
        }

        private static TimeRange of(LocalTime start, LocalTime end) {
            return new TimeRange(start, end);
        }

        private static TimeRange of(TimeRange other) {
            return new TimeRange(other.start, other.end);
        }

        private TimeRanges merge(TimeRange other) {
            validate(other);
            if (this.end.isBefore(other.start)) {
                return TimeRanges.of(this, other);
            }
            if (other.end.isBefore(this.start)) {
                return TimeRanges.of(other, this);
            }
            LocalTime newStart;
            LocalTime newEnd;
            if (!this.start.isAfter(other.start)) {
                newStart = this.start;
            } else {
                newStart = other.start;
            }
            if (!this.end.isBefore(other.end)) {
                newEnd = this.end;
            } else {
                newEnd = other.end;
            }
            return TimeRanges.of(of(newStart, newEnd));
        }

        private TimeRanges exclude(TimeRange other) {
            validate(other);
            if (!this.end.isAfter(other.start)
                || !other.end.isAfter(this.start)) {
                return TimeRanges.of(this);
            }
            if (!other.start.isAfter(this.start)) {
                if (!this.end.isAfter(other.end)) {
                    return TimeRanges.empty();
                } else {
                    return TimeRanges.of(of(other.end, this.end));
                }
            } else {
                if (!this.end.isAfter(other.end)) {
                    return TimeRanges.of(of(this.start, other.start));
                } else {
                    return TimeRanges.of(of(this.start, other.start), of(other.end, this.end));
                }
            }
        }

        private static void validate(TimeRange range) {
            if (range == null || range.start == null || range.end == null
                || range.end.isBefore(range.start)) {
                throw new IllegalArgumentException("Invalid TimeRange parameters");
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TimeRange timeRange = (TimeRange) o;
            return Objects.equals(start, timeRange.start) &&
                Objects.equals(end, timeRange.end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return "TimeRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
        }
    }

}
