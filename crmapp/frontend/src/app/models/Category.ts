import { TreeNode } from 'primeng/primeng';

export class Category implements TreeNode {
        public id?: number;
        public label?: string;
        public collapsedIcon?: any;
        public expandedIcon?: any;
        public data: any;
        public urlPath?: string;
        public comment?: string;
}