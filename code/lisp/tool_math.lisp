

;; ------------ 从 1 累加到 n ------------
(defun triangle (n)
  (labels ((tri (c n)
             (declare (type fixnum n c))
             (if (zerop n)
                 c
                 (tri (the fixnum (+ n c))
                      (the fixnum (- n 1))))))
    (tri 0 n)))



;; --------------- 平均数-----------------
(defmacro avg (&rest args)
  `(/ (+ ,@args) ,(length args)))

;; --------------------------------------
(defmacro sum (&rest args)
  `(+ ,@args))

;; ---------- ----------
(defun fib (n)
  (do ((i n (- i 1))
       (f1 1 (+ f1 f2))
       (f2 1 f1))
      ((<= i 1) f1)))
