import * as estree from 'estree';
import ts from 'typescript';
import { RequiredParserServices } from 'eslint-plugin-sonarjs/lib/utils/parser-services';
export declare function isArray(node: estree.Node, services: RequiredParserServices): boolean;
export declare function isString(node: estree.Node, services: RequiredParserServices): boolean;
export declare function isNumber(node: estree.Node, services: RequiredParserServices): boolean;
export declare function isStringType(type: ts.Type): boolean;
export declare function isFunction(node: estree.Node, services: RequiredParserServices): boolean;
export declare function isUndefinedOrNull(node: estree.Node, services: RequiredParserServices): boolean;
export declare function isAny(type: ts.Type): boolean;
export declare function getTypeFromTreeNode(node: estree.Node, services: RequiredParserServices): ts.Type;
export declare function getTypeAsString(node: estree.Node, services: RequiredParserServices): string;
export declare function getSymbolAtLocation(node: estree.Node, services: RequiredParserServices): ts.Symbol | undefined;
export declare function getSignatureFromCallee(node: estree.Node, services: RequiredParserServices): ts.Signature | undefined;
