;; 加载tool.lisp
(load (compile-file "tool.lisp"))


;; Problem 1
(defun main001 ()
  (let* ((vec   (map-int #'identity 999))
         (vec2  (remove-if
                   #'(lambda (y) (zerop (mod y 5)))
                   (remove-if
                      #'(lambda (x) (zerop (mod x 3)))
                      vec ))))
     (apply #'+ (set-difference vec vec2))
   )
  )


;; Problem 2
(defun main002 ()
 (fib-even-sum 32))

(defun fib-even-sum (n)
  (do ((i n (- i 1))
       (f1 1 (+ f1 f2))
       (f2 1 f1)

       (sum 0 (if (evenp f1) (+ sum f1) sum))
      )
      ((<= i 0) sum)))


;; Problem 3
(defun maxqu (num)
  (do ((i 2 (1+ i)))
      ((>= i num)
    (and (zerop (mod num i))
         (maxqu (/ num i)))
    )
  )


;; Problem 4

(defun palindrome? (x)
  (let ((mid (/ (length x) 2)))
    (equal (subseq x 0 (floor mid))
           (reverse (subseq x (ceiling mid))))))

(defun test ()
  (for i 1 3
    (for j 2 4
     ; (if (palindrome? (write-to-string (* i j)))
     ;    (return  (* i j)))
      (return  j )
      )
       )

  )
