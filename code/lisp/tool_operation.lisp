
;; ! 对应的破坏版本
;; (delete-if #'oddp list)
;; <=> (funcall (! #'remove-if) #'oddp lst)

(defvar *!equivs* (make-hash-table))

(defun ! (fn)
  (or (gethash fn *!equivs*) fn))

(defun def! (fn fn!)
  (setf (gethash fn *!equivs*) fn!))
;; ------------------------
(def! #'remove-if #'delete-if)





;; -------------------------


(defmacro while (test &body body)
  `(do ()
       ((not ,test))
     ,@body))

;; ------------------------
(defmacro for (var start stop &body body)
  (let ((gstop (gensym)))
    `(do ((,var ,start (1+ ,var))
          (,gstop ,stop))
         ((> ,var ,gstop))
       ,@body)))

;; ----- 带返回值 --------
(defmacro for2 (var start stop &body body)
  (let ((gstop (gensym)))
    `(do ((,var ,start (1+ ,var))
          (,gstop ,stop))
         ((>= ,var ,gstop) ,@body)
       )))

;; ------------------------
(defmacro in (obj &rest choices)
  (let ((insym (gensym)))
    `(let ((,insym ,obj))
       (or ,@(mapcar #'(lambda (c) `(eql ,insym ,c))
                     choices)))))


;; ------------------------
(defmacro aif (test then &optional else)
  `(let ((it ,test))
     (if it ,then ,else)))


;; -----------------------
(defmacro mac (expr)
  `(pprint (macroexpand-1 ',expr)))
