;; -------------- 操作列表的小函数-------------------
(proclaim '(inline last1 single append1 conc1 mklist))

;; ------------------------
(defun last1 (lst)
  (car (last lst)))

;; ------------------------
(defun single (lst)
  (and (consp lst) (not (cdr lst))))

;; ------------------------
(defun append1 (lst obj)
  (append lst (list obj)))

;; ------------------------
(defun conc1 (lst obj)
  (nconc lst (list obj)))

;; ------------------------
(defun mklist (obj)
  (if (listp obj) obj (list obj)))



;; -----------------操作列表的较大函数-------------------

;; ------------------------

(defun longer (x y)
  (labels ((compare (x y)
             (and (consp x)
                  (or (null y)
                      (compare (cdr x) (cdr y))))))
    (if (and (listp x) (listp y))
        (compare x y)
        (> (length x) (length y)))))

;; ------------------------
(defun filter (fn lst)
  (let ((acc nil))
    (dolist (x lst)
      (let ((val (funcall fn x)))
        (if val (push val acc))))
    (nreverse acc)))

;; ------------------------
(defun group (source n)
  (if (zerop n) (error "zero length"))
  (labels ((rec (source acc)
             (let ((rest (nthcdr n source)))
               (if (consp rest)
                   (rec rest (cons (subseq source 0 n) acc))
                   (nreverse (cons source acc))))))
    (if source (rec source nil) nil)))

;; ------------------------
(defun flatten (x)
  (labels ((rec (x acc)
             (cond ((null x) acc)
                   ((atom x) (cons x acc))
                   (t (rec (car x) (rec (cdr x) acc))))))
    (rec x nil)))

;; ------------------------
(defun prune (test tree)
  (labels ((rec (tree acc)
             (cond ((null tree) (nreverse acc))
                   ((consp (car tree))
                    (rec (cdr tree)
                         (cons (rec (car tree) nil) acc)))
                   (t (rec (cdr tree)
                           (if (funcall test (car tree))
                               acc
                               (cons (car tree) acc)))))))
    (rec tree nil)))



;; ------------------- 搜索列表的函数 ------------------------
;; ------------------------
(defun find2 (fn lst)
  (if (null lst)
      nil
      (let ((val (funcall fn (car lst))))
        (if val
            (values (car lst) val)
            (find2 fn (cdr lst))))))

;; ------------------------
(defun before (x y lst &key (test #'eql))
  (and lst
       (let ((first (car lst)))
         (cond ((funcall test y first) nil)
               ((funcall test x first) lst)
               (t (before x y (cdr lst) :test test))))))

;; ------------------------
(defun after (x y lst &key (test #'eql))
  (let ((rest (before y x lst :test test)))
    (and rest (member x rest :test test))))

;; ------------------------
(defun duplicate (obj lst &key (test #'eql))
  (member obj (cdr (member obj lst :test test))
          :test test))

;; ------------------------
(defun split-if (fn lst)
  (let ((acc nil))
    (do ((src lst (cdr src)))
        ((or (null src) (funcall fn (car src)))
         (values (nreverse acc) src))
      (push (car src) acc))))

;; ------------------------

;; ---------------- 带有元素比较的搜索函数 ------------------

;; ------------------------
(defun most (fn lst)
  (if (null lst)
      (values nil nil)
      (let* ((wins (car lst))
             (max (funcall fn wins)))
        (dolist (obj (cdr lst))
          (let ((score (funcall fn obj)))
            (when (> score max)
              (setq wins obj
                    max score))))
        (values wins max))))

;; ------------------------
(defun best (fn lst)
  (if (null lst)
      nil
      (let ((wins (car lst)))
        (dolist (obj (cdr lst))
          (if (funcall fn obj wins)
              (setq wins obj)))
        wins)))

;; ------------------------
(defun mostn (fn lst)
  (if (null lst)
      (values nil nil)
      (let ((result (list (car lst)))
            (max (funcall fn (car lst))))
        (dolist (obj (cdr lst))
          (let ((score (funcall fn obj)))
            (cond ((> score max)
                   (setq max score
                         result (list obj)))
                  ((= score max)
                   (push obj result)))))
        (values (nreverse result) max))))



;; ----------------------- 映射函数 -----------------------------

(defun map-int (fn n)
  (mapa-b fn 1 n))

;; ------------------------
(defun mapa-b (fn a b &optional (step 1))
  (do ((i a (+ i step))
       (result nil))
      ((> i b) (nreverse result))
    (push (funcall fn i) result)))

;; ------------------------
(defun map-> (fn start test-fn succ-fn)
  (do ((i start (funcall succ-fn i))
       (result nil))
      ((funcall test-fn i) (nreverse result))
    (push (funcall fn i) result)))

;; ------------------------
(defun mappend (fn &rest lsts)
  (apply #'append (apply #'mapcar fn lsts)))

;; ------------------------
(defun mapcars (fn &rest lsts)
  (let ((result nil))
    (dolist (lst lsts)
      (dolist (obj lst)
        (push (funcall fn obj) result)))
    (nreverse result)))

;; ------------------------
(defun rmapcar (fn &rest args)
  (if (some #'atom args)
      (apply fn args)
      (apply #'mapcar
             #'(lambda (&rest args)
                 (apply #'rmapcar fn args))
             args)))



;; ------------------------ I/O 函数 -------------------------------

(defun readlist (&rest args)
  (values (read-from-string
           (concatenate 'string "("
                        (apply #'read-line args)
                        ")"))))

;; ------------------------
(defun prompt (&rest args)
  (apply #'format *query-io* args)
  (read *query-io*))

;; ------------------------
(defun break-loop (fn quit &rest args)
  (format *query-io* "Entering break-loop.'~%")
  (loop
    (let ((in (apply #'prompt args)))
      (if (funcall quit in)
          (return)
          (format *query-io* "~A~%" (funcall fn in))))))



;; --------------------- 操作符号和字符串的函数 -----------------------

(defun mkstr (&rest args)
  (with-output-to-string (s)
    (dolist (a args) (princ a s))))

;; ------------------------
(defun symb (&rest args)
  (values (intern (apply #'mkstr args))))

;; ------------------------
(defun reread (&rest args)
  (values (read-from-string (apply #'mkstr args))))

;; ------------------------
(defun explode (sym)
  (map 'list #'(lambda (c)
                 (intern (make-string 1
                                      :initial-element c)))
       (symbol-name sym)))



;; -------------------------- 宏函数 ---------------------------------

;; ------------------------
(defmacro random-choice (&rest exprs)
  `(case (random ,(length exprs))
     ,@(let ((key -1))
         (mapcar #'(lambda (expr)
                     `(,(incf key) ,expr))
                 exprs))))

;; ------------------------
(defmacro with-gensyms (syms &body body)
  `(let ,(mapcar #'(lambda (s)
                     `(,s (gensym)))
          syms)
     ,@body))


;; ==============
;; --------------------------- 函数构造器 -----------------------------
(defun compose (&rest fns)
  (destructuring-bind (fn1 . rest) (reverse fns)
    #'(lambda (&rest args)
        (reduce #'(lambda (v f) (funcall f v))
                rest
                :initial-value (apply fn1 args)))))

;; <=> #'(lambda (&rest args) (a (b (apply #'c args))))

;; ------------------------
(defun disjoin (fn &rest fns)
  (if (null fns)
      fn
      (let ((disj (apply #'disjoin fns)))
        #'(lambda (&rest args)
            (or (apply fn args) (apply disj args))))))

;; ------------------------
(defun conjoin (fn &rest fns)
  (if (null fns)
      fn
      (let ((conj (apply #'conjoin fns)))
        #'(lambda (&rest args)
            (and (apply fn args) (apply conj args))))))

;; ------------------------
(defun curry (fn &rest args)
  #'(lambda (&rest args2)
      (apply fn (append args args2))))

;; ------------------------
(defun rcurry (fn &rest args)
  #'(lambda (&rest args2)
      (apply fn (append args2 args))))

;; ------------------------
(defun always (x) #'(lambda (&rest args) x))

;; ------------------------
(defun fif (if then &optional else)
  #'(lambda (x)
      (if (funcall if x)
          (funcall then x)
          (if else (funcall else x)))))
;; eg.
;; (mapcar #'(lambda (x)
;;             (if (slave x)
;;                 (owner x)
;;                 (employer x)))
;;         people)
;; <=>
;; (mapcar (fif #'slave #'owner #'employer)  people)

;; -----------------------
;; 返回一组谓词的交
(defun fint (fn &rest fns)
  (if (null fns)
      fn
      (let ((chain (apply #'fint fns)))
        #'(lambda (x)
            (and (funcall fn x) (funcall chain x))))))
;; eg.
;; (find-if #'(lambda (x)
;;              (and (signed x) (sealed x) (delivered x)))
;;          docs)
;; <=>
;; (find-if (fint #'signed #'sealed #'delivered) docs)

;; ---------------------
;; 返回一组谓词的并
(defun fun (fn &rest fns)
  (if (null fns)
      fn
      (let ((chain (apply #'fun fns)))
        #'(lambda (x)
            (or (funcall fn x) (funcall chain x))))))

