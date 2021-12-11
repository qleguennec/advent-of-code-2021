(ns advent-of-code.core
  (:require
   [advent-of-code.tap :refer [tap]]
   [advent-of-code.input :as input]
   [taoensso.encore :as encore]
   [clojure.string :as str]
   [clojure.math.combinatorics :as combo]))

(defn parse-int [x]
  (Integer/parseUnsignedInt x))

(defn pow [x n]
  (Math/pow x n))

(defn alt
  ([c t]
   (alt c t identity))
  ([c t f]
   (fn [x]
     ((if (c x)
        t f)
      x))))

(defn bidirectional-inclusive-range [a b]
  (range a ((if (> a b) dec inc) b) (compare b a)))

(defn median [coll]
  (let [c (count coll)]
    (nth (sort coll)
         (Math/ceil (/ c 2.0)))))

(defn abs [x]
  (Math/abs x))

(defn day-5.2 []
  (let [input "299,462 299,747
855,314 855,140
981,328 798,328
610,444 680,374
797,242 606,242
217,42 147,42
735,378 735,188
247,192 912,192
377,341 768,341
472,701 66,701
48,970 885,133
893,35 664,35
617,237 951,237
540,643 190,293
575,815 302,815
146,380 146,562
568,481 568,161
38,101 921,984
613,12 185,12
967,30 17,980
823,620 584,859
672,822 413,822
259,626 385,752
752,415 857,310
758,659 758,76
909,893 35,19
964,913 105,54
697,196 697,913
389,821 163,821
783,65 281,65
775,732 558,732
818,817 42,817
499,537 896,140
81,957 81,844
851,256 559,548
268,970 268,170
106,216 68,178
107,371 850,371
160,107 748,107
300,619 524,395
940,196 780,356
752,498 752,94
807,619 728,619
831,89 313,89
56,389 191,524
206,75 206,816
486,924 486,389
280,708 542,446
562,917 190,545
40,231 40,404
804,327 726,249
538,670 170,302
473,229 912,668
645,195 645,916
502,13 502,266
639,955 639,434
87,56 943,912
143,798 699,798
469,261 79,651
715,98 104,709
914,339 463,790
456,263 456,101
656,105 109,105
28,944 123,944
981,652 270,652
953,681 605,333
474,858 310,858
542,736 807,736
234,412 620,26
615,786 36,207
169,56 169,132
133,930 989,74
342,34 516,34
210,97 947,834
43,857 824,76
673,840 673,156
718,123 896,123
673,311 673,564
639,352 72,919
552,571 661,462
819,335 953,335
756,84 823,84
250,969 287,969
551,260 378,433
417,412 465,412
621,260 249,632
226,633 394,465
475,179 602,306
272,571 272,839
820,666 820,829
988,608 974,608
124,318 124,589
303,516 839,516
983,477 983,786
299,870 927,242
284,875 213,875
427,493 545,493
74,755 17,698
294,326 294,23
331,193 391,193
381,671 408,644
805,537 805,155
721,956 10,956
918,36 918,915
981,891 445,355
591,288 933,288
199,256 250,307
318,810 789,339
250,245 522,517
592,248 958,614
722,215 235,215
654,496 654,905
76,860 678,258
29,20 954,945
379,851 567,851
722,161 13,870
965,302 390,877
114,892 114,348
265,681 26,920
94,463 94,160
340,150 340,759
727,612 175,60
457,951 154,648
602,200 602,841
487,194 27,654
356,699 887,168
915,237 262,890
81,225 815,959
227,877 694,877
441,674 441,968
865,201 865,528
969,214 511,214
802,748 802,86
662,313 636,313
308,447 308,545
245,236 532,236
621,195 621,140
889,159 197,851
68,683 179,572
859,261 56,261
982,539 982,619
144,362 851,362
304,905 304,553
551,905 637,905
432,316 142,606
104,588 104,862
316,392 680,392
372,413 866,413
874,53 697,53
499,668 499,329
32,207 802,977
403,108 100,411
578,442 578,489
134,161 848,875
851,935 95,179
485,190 485,57
411,47 680,47
378,878 378,127
908,717 516,717
432,863 328,863
212,278 212,326
552,426 933,807
419,329 492,402
975,750 424,750
40,54 915,929
570,349 576,349
32,784 32,473
854,407 343,407
18,932 425,932
223,571 468,816
939,330 939,870
126,637 105,616
84,310 84,788
491,890 229,890
737,831 737,726
137,471 137,957
642,429 253,429
319,103 903,103
38,872 38,110
809,183 809,653
877,87 56,908
455,136 693,374
218,647 727,647
626,544 797,544
147,46 122,46
316,430 495,430
608,469 331,192
353,769 714,769
649,90 410,90
105,311 105,674
594,600 484,600
822,933 279,933
478,267 478,341
114,912 114,387
843,480 754,480
747,701 747,143
646,88 646,375
195,129 757,691
470,895 470,673
450,595 334,711
165,872 165,155
744,947 987,947
153,15 357,15
272,222 272,201
535,551 120,966
102,748 102,281
348,482 129,482
499,679 499,821
399,875 399,285
695,585 733,547
40,509 95,564
199,857 228,886
491,885 978,885
926,887 104,65
492,128 957,593
302,904 302,906
706,782 706,217
721,506 721,675
847,725 583,989
225,734 942,17
141,161 161,161
858,736 858,433
183,724 13,554
299,647 299,420
623,39 358,304
657,373 657,976
452,714 452,735
857,537 392,72
758,979 758,457
141,609 141,100
266,76 974,784
527,66 236,357
971,176 865,282
961,935 74,48
328,434 328,663
384,670 12,670
534,508 334,508
336,603 202,469
690,140 807,140
491,511 491,166
265,493 236,493
552,113 552,329
370,542 370,688
919,556 488,125
142,949 107,949
917,824 360,267
866,109 624,109
714,657 714,155
727,567 727,570
235,920 235,683
329,261 55,261
672,718 113,159
469,380 66,783
884,289 884,15
412,197 496,197
971,875 20,875
831,245 831,946
22,985 391,985
984,136 187,933
845,334 660,519
367,299 367,912
25,985 946,64
487,416 487,453
89,223 723,857
890,953 19,82
199,256 199,521
785,981 710,906
673,160 673,682
65,730 421,730
957,89 832,89
647,361 33,361
243,347 784,888
50,760 50,372
564,278 564,846
344,652 832,652
219,586 219,502
976,500 976,650
515,265 744,36
930,730 930,332
479,48 592,48
979,326 374,326
790,520 375,520
129,919 511,537
203,558 212,558
688,842 502,842
979,976 90,87
78,929 78,478
578,203 802,427
788,360 260,888
847,342 212,977
256,578 821,13
493,561 712,342
90,116 181,116
317,736 963,90
453,548 37,548
15,472 514,971
972,579 956,579
456,66 349,66
102,771 769,771
320,741 327,748
981,150 592,150
739,978 739,804
861,10 142,729
457,596 580,596
358,287 237,408
705,719 59,73
27,770 27,133
846,690 846,464
138,351 174,315
380,942 380,985
490,421 803,734
559,449 761,449
709,218 718,218
271,877 271,814
89,845 918,16
613,436 976,73
27,88 867,88
541,965 739,767
187,746 916,17
294,13 333,13
600,647 925,647
915,942 41,68
38,625 176,763
468,905 468,727
337,89 337,581
48,969 732,285
555,301 555,610
155,525 985,525
235,167 700,167
728,134 728,289
696,595 892,595
983,696 401,114
581,40 515,40
171,837 975,33
588,683 734,683
880,132 231,132
847,145 332,660
657,179 657,18
49,957 496,510
791,497 552,736
988,989 10,11
937,659 937,461
403,458 403,637
237,765 237,813
35,504 35,663
556,897 802,897
382,491 786,895
103,566 528,566
598,570 623,570
345,343 345,985
537,59 537,386
207,811 974,44
463,623 463,21
966,915 966,965
569,281 569,183
470,648 470,666
441,420 817,796
451,723 908,266
300,297 840,297
902,201 902,912
598,930 654,930
874,433 874,176
551,967 795,967
892,23 137,778
306,463 679,90
16,78 16,980
782,749 782,117
235,240 244,231
461,183 981,183
608,170 608,640
75,711 645,141
49,238 488,238
14,963 947,30
120,56 120,73
630,978 316,664
219,389 803,973
106,918 978,46
941,439 788,592
313,943 325,943
325,683 325,67
774,816 774,908
608,833 608,679
447,289 447,135
405,650 405,406
622,467 636,467
855,606 855,663
918,640 918,831
640,869 640,904
481,405 481,791
185,582 21,746
556,772 114,330
490,144 490,591
60,74 974,988
967,978 10,21
159,669 486,342
302,636 302,771
841,427 793,427
670,743 234,743
47,676 233,490
877,768 123,14
139,462 139,541
204,59 204,300
720,702 720,525
171,341 787,341
699,899 293,899
431,513 431,849
904,278 124,278
919,67 815,67
401,519 688,232
675,279 675,137
376,786 362,786
57,817 801,73
809,468 410,867
669,171 669,65
520,36 218,36
702,159 709,159
399,646 399,828
853,759 853,91
58,143 867,952
896,939 42,85
677,120 646,120
947,714 737,714
515,107 752,344
793,142 793,789
86,896 967,15
663,493 833,493
986,766 293,766
71,874 71,417
471,426 148,426
444,982 142,982
124,582 846,582
336,436 257,436
877,750 177,50
69,73 911,915
315,363 315,45
620,272 556,272
616,186 331,186
766,756 59,49
555,271 555,183
437,246 454,246
169,57 169,688
448,605 420,605
194,149 960,915
597,308 597,512
328,337 328,349
506,331 506,144
608,633 838,863
37,99 767,829
128,487 128,246
473,303 473,529
754,890 754,269
854,958 957,958
704,360 526,360
613,752 260,752
179,302 805,302
916,176 519,176
318,622 161,622
783,785 322,785
148,289 802,943
944,280 671,280
758,402 442,86
988,959 56,27
716,642 429,642
899,48 899,111
981,325 168,325
603,77 474,77
103,387 112,387
100,40 160,40
969,317 109,317
938,424 938,179
834,980 527,980
875,83 22,83
89,572 582,79
642,862 642,247
499,26 499,242
873,173 206,840
314,112 314,388
778,25 778,944
902,457 964,519
822,891 623,891
538,76 618,156
418,179 204,179
138,131 300,131
51,386 51,764
756,728 330,302
801,374 801,506
239,416 781,958
311,651 848,651
67,586 67,250
794,244 794,515
228,810 368,670
399,561 682,561
919,61 868,61
204,253 224,253
74,657 74,235
208,422 722,422
246,353 441,548
362,175 362,688
403,681 403,821
146,183 23,183"
        input (->> input
                   str/split-lines
                   (map #(str/split % #" "))
                   (map (partial mapv (comp (partial mapv parse-int) #(str/split % #",")))))
        hit-coords
        (->> input
             (mapcat
              (fn [[[x0 y0] [x1 y1]]]
                (encore/cond
                  (= x0 x1) (map vector (repeat x0) (bidirectional-inclusive-range y0 y1))
                  (= y0 y1) (map vector (bidirectional-inclusive-range x0 x1) (repeat y0))
                  (map vector
                     (bidirectional-inclusive-range x0 x1)
                     (bidirectional-inclusive-range y0 y1))))))
        height (apply max (map first hit-coords))
        width (apply max (map second hit-coords))
        grid (->> (for [x (range 0 (inc height))
                      y (range 0 (inc width))]
                    [[x y] 0])
                  (into {}))]
    (->> hit-coords
         (reduce
          (fn [grid coords]
            (update grid coords inc))
          grid)
         (filter (fn [[_ hits]] (>= hits 2)))
         count)))

(defn day-6.1 []
  (let [input (->> (str/split input/day-6 #",")
                   (map parse-int))]
    (->> (reduce
          (fn [fishes _]
            (reduce
             (fn [fishes fish]
               (if (zero? fish)
                 (into fishes [6 8])
                 (conj fishes (dec fish))))
             []
             fishes))
          input
          (range 80))
         count)))

(defn day-6.2 []
  (let [input (->> (str/split input/day-6 #",")
                   (map parse-int)
                   frequencies)]
    (->> (reduce
          (fn [fishes _]
            (reduce-kv
             (fn [fishes days day-fishes]
               (if (zero? days)
                 (-> fishes
                     (update 6 + day-fishes)
                     (update 8 + day-fishes))
                 (-> fishes
                     (update (dec days) + day-fishes))))
             (into {} (map vector (range 9) (repeat 0)))
             fishes))
          input
          (range 256))
         (map second)
         (reduce + 0))))

(defn day-7.1 []
  (let [input input/day-7
        input-median
        (median input)]
    (->> input
         (map (comp abs (partial - input-median)))
         (reduce + 0))))

(defn day-7.2 []
  (let [input input/day-7
        width (apply max input)]
    (->> input
         (map
          (fn [crab]
            (concat
             (->> (range 1 (inc crab))
                  (reduce
                   (fn [path x]
                     (conj path (+ x (or (first path) 0))))
                   (list)))
             (list 0)
             (->> (range 1 (- width crab))
                  (reduce
                   (fn [path x]
                     (conj path (+ x (or (first path) 0))))
                   (list))
                  reverse))))
         (apply map list)
         (map (partial reduce + 0))
         (reduce min encore/max-long))))
