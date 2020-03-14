;; - Otherwise, PACKAGE is already referenced by another Spacemacs layer, so

(defconst ljq-packages
  '(
    youdao-dictionary
    all-the-icons
    )
)
;;; packages.el ends here



;; 初始化 package
;; 可以使用 , d m 快捷键, 然后按下 e 展开宏
(defun ljq/init-youdao-dictionary ()
  (use-package youdao-dictionary
    :defer t
    :init
    (spacemacs/set-leader-keys "dd" 'youdao-dictionary-search-at-point+)
    )
  )

;; undo
(defun ljq/init-all-the-icons()
  (use-package all-the-icons)
  )

